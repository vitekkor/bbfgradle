// Original bug: KT-17588

class A {
    class D(
            val i: Int? = null,
            val b: Boolean? = null
    )

    init {
        var nflag: Boolean? = null

        val a2: String = "123"
        try {
            a2.let { a2 ->
                nflag = false
            }
        } finally {
            a2.hashCode()
        }
        D(null, nflag)
    }
}
