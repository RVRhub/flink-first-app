package dev.rvr

import org.apache.flink.api.common.functions.RuntimeContext
import org.apache.flink.api.java.ExecutionEnvironment
import org.apache.flink.api.java.operators.DataSource
import org.apache.flink.api.java.tuple.Tuple3
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment
import org.apache.flink.streaming.connectors.elasticsearch7.ElasticsearchSink
import org.apache.flink.streaming.connectors.elasticsearch.RequestIndexer
import org.apache.http.HttpHost
import org.apache.logging.log4j.LogManager
import org.elasticsearch.client.Requests
import java.io.File
import java.util.*

class BatchToElasticsearch {
    companion object {
        private val LOG = LogManager.getLogger(BatchToElasticsearch::class.java)

        @JvmStatic
        fun main(args: Array<String>) {
            val env = ExecutionEnvironment.getExecutionEnvironment()
            val streamEnv = StreamExecutionEnvironment.getExecutionEnvironment()

            LOG.info("Test log message")
            LOG.info("Current working directory: ${System.getProperty("user.dir")}")
            LOG.info("Check if file exists: ${File("/opt/flink/data.cvs").exists()}")

            val data: DataSource<PersonDto> = ExecutionEnvironment.getExecutionEnvironment().readCsvFile("/opt/flink/data.cvs")
                .includeFields("111")
                .pojoType(PersonDto::class.java, "id", "lastname", "firstname")

            val httpHosts = arrayListOf(HttpHost("localhost", 9200, "http"))

            val esSinkBuilder = ElasticsearchSink.Builder<PersonDto>(
                httpHosts
            ) { element, ctx: RuntimeContext, indexer: RequestIndexer ->
                val json = mapOf(
                    "id" to element.id,
                    "lastname" to element.lastname,
                    "firstname" to element.firstname
                )
                val rqst = Requests.indexRequest()
                    .index("dev_index")
                    .source(json)
                indexer.add(rqst)
            }

            esSinkBuilder.setBulkFlushMaxActions(500)
            esSinkBuilder.setBulkFlushInterval(2000)

            val processedStream = streamEnv.fromCollection(data.collect())
            processedStream.addSink(esSinkBuilder.build())

            streamEnv.execute("Flink Batch to Elasticsearch")
        }
    }
}


data class PersonDto(
    var id: String = "",
    var lastname: String = "",
    var firstname: String = ""
)