package dev.rvr.utils

import org.apache.flink.api.common.functions.MapFunction
import org.apache.logging.log4j.LogManager

class LoggingFunction : MapFunction<String, String> {
    private val LOG = LogManager.getLogger(LoggingFunction::class.java)

    override fun map(value: String): String {
        LOG.info("Processed value: $value")
        return value
    }
}