// Original bug: KT-3374

class Test {
    private fun <T : Any> T?.self() = object {
        fun calc() : T {
            return this@self!!
        }
    }


    fun box()  {
        1.self().calc() + 1
    }
}

fun main(args: Array<String>) {
    Test().box()
}
