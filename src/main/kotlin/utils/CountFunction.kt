package dev.rvr.utils

import org.apache.flink.api.common.functions.RichMapFunction
import org.apache.flink.configuration.Configuration
import org.apache.flink.api.common.state.ValueState
import org.apache.flink.api.common.state.ValueStateDescriptor

class CountFunction : RichMapFunction<String, String>() {
    @Transient
    private var countState: ValueState<Int>? = null

    override fun open(config: Configuration) {
        val descriptor = ValueStateDescriptor("count", Int::class.java, 0)
        countState = runtimeContext.getState(descriptor)
    }

    override fun map(value: String): String {
        Thread.sleep(1000)

        val currentCount = countState?.value() ?: 0
        countState?.update(currentCount + 1)
        return "$value, count: $currentCount"
    }
}

