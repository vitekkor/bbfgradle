// Original bug: KT-9877

fun <T> T.noInline(p: (T) -> Unit) {
    p(this)
}

inline fun inlineCall(p: () -> Unit) {
    p()
}

fun foo() {
    val loci = listOf("a", "b", "c")
    val gene = "g1"

    inlineCall {
        val value = 10.0
        loci.forEach { 
            var locusMap = 1.0
            gene.noInline { gene ->
                locusMap = value
            }
        }
    }
}

fun main(args: Array<String>) {
    print(foo())
}
