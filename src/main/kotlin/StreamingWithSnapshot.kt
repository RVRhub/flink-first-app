package dev.rvr

import dev.rvr.utils.CountFunction
import dev.rvr.utils.LoggingFunction
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment
import org.apache.flink.streaming.api.datastream.DataStream
import org.apache.logging.log4j.LogManager

class StreamingWithSnapshot {
    companion object {
        private val LOG = LogManager.getLogger(StreamingWithSnapshot::class.java)

        private const val JOB_NAME = "StreamingWithSnapshot"

        @JvmStatic
        fun main(args: Array<String>) {
            val env = StreamExecutionEnvironment.getExecutionEnvironment()
            LOG.info("Stream execution environment: $env")

            env.enableCheckpointing(1000)

            val text: DataStream<String> = env.fromSequence(1, 100)
                .map { it.toString() }
            val keyedStream = text.keyBy { it.length }
            LOG.info("Keyed stream: $keyedStream")
            val processed: DataStream<String> = keyedStream
                .map(CountFunction())
//                .map(LoggingFunction())
            processed.print()

            env.executeAsync("$JOB_NAME-${System.currentTimeMillis()}")
        }
    }
}