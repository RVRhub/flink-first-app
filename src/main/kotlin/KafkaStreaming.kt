package dev.rvr

import org.apache.flink.api.common.eventtime.WatermarkStrategy
import org.apache.flink.api.common.serialization.SimpleStringSchema
import org.apache.flink.connector.base.DeliveryGuarantee
import org.apache.flink.connector.kafka.sink.KafkaRecordSerializationSchema
import org.apache.flink.connector.kafka.sink.KafkaSink
import org.apache.flink.connector.kafka.source.KafkaSource
import org.apache.flink.connector.kafka.source.enumerator.initializer.OffsetsInitializer
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment
import org.apache.logging.log4j.LogManager

class KafkaStreaming {

    companion object {
        private val LOG = LogManager.getLogger(KafkaStreaming::class.java)

        private const val JOB_NAME = "KafkaStreaming"

        @JvmStatic
        fun main(args: Array<String>) {
            val env = StreamExecutionEnvironment.getExecutionEnvironment()
            LOG.info("Stream execution environment: $env")

            val inputTopicName = "input-topic"
            val outputTopicName = "output-topic"

            val kafkaSource = KafkaSource.builder<String>()
                .setBootstrapServers("host.docker.internal:29092")
                .setTopics(inputTopicName)
                .setGroupId("test-id")
                .setStartingOffsets(OffsetsInitializer.earliest())
                .setValueOnlyDeserializer(SimpleStringSchema())
                .build();

            val kafkaSink: KafkaSink<String> = KafkaSink.builder<String>()
                .setBootstrapServers("host.docker.internal:29092")
                .setRecordSerializer(
                    KafkaRecordSerializationSchema.builder<Any>()
                        .setTopic(outputTopicName)
                        .setValueSerializationSchema<String>(SimpleStringSchema())
                        .build()
                )
                .setDeliveryGuarantee(DeliveryGuarantee.AT_LEAST_ONCE)
                .build()



            val stream = env.fromSource(kafkaSource, WatermarkStrategy.noWatermarks(), "Kafka Source")
            stream.sinkTo(kafkaSink)

            env.execute("$JOB_NAME-${System.currentTimeMillis()}")
        }

    }
}