package dev.rvr

import org.apache.flink.api.common.functions.FlatMapFunction
import org.apache.flink.api.java.tuple.Tuple2
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment
import org.apache.flink.util.Collector
import java.util.*

class Tokenizer : FlatMapFunction<String, Tuple2<String, Int>> {
    override fun flatMap(value: String, out: Collector<Tuple2<String, Int>>) {
        val words = value.lowercase(Locale.getDefault()).split("\\W+".toRegex())

        for (word in words) {
            if (word.isNotEmpty()) {
                out.collect(Tuple2.of(word, 1))
            }
        }
    }
}

class WordCount {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val env = StreamExecutionEnvironment.getExecutionEnvironment()
            val text = env.socketTextStream("localhost", 9999)

            val counts = text
                .flatMap(Tokenizer())
                .keyBy { it.f0 }
                .sum(1)

            counts.print()

            env.execute("WordCount")
        }
    }
}
