// Original bug: KT-9877

fun <T> Collection<T>.parallelStream(): java.util.stream.Stream<T> {
    return (this as java.util.Collection<T>).parallelStream()
}

interface Processor {
    operator fun invoke(a: String, b: String) {
        print(a)
    }
}

data class Config(val id: String)

fun Config.foo(s: String) {
    val loci = listOf("a", "b", "c")
    val genes = listOf("g1", "g2", "g3")
    (object : Processor {
        override fun invoke(a: String, b: String) {
            (1..10).forEach {
                val coverage = 123.0
                val perMillion = (1..200).map { coverage * 123 }.sum() / 10e6
                loci.forEach { locus ->
                    val locusMap = hashMapOf<String, Double>()
                    genes.parallelStream().forEach { gene ->
                        val length = (1..200).sum()
                        if (length > 0) {
                            val c = loci
                                    .map {
                                        coverage * 123
                                    }.sum()
                            val rpkm = c / (length / 10e3) / perMillion
                            locusMap[gene] = rpkm
                        }
                    }
                }
            }
        }
    })("bar", s)
}

class Main {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            print(Config("bla").foo("bar"))
        }
    }
}

