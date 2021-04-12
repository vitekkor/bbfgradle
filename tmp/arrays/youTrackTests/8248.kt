// Original bug: KT-18839

class Some {
    init {
        val x = 128
        println(x)

        run {
            println(x)
        }
        fun some() {
            val b = 299
            println(b + x)
        }
    }
}
