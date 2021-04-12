// Original bug: KT-9878

inline fun inlineCall(p: () -> Unit) {
    p()
}

fun foo() {
    val loci = listOf("a", "b", "c")
    var gene = "g1"

    inlineCall {
        val value = 10.0
        loci.forEach {
            var locusMap = 1.0
            {
                locusMap = value
                gene = "OK"
            }()
        }
    }
}

fun main(args: Array<String>) {
    print(foo())
}
