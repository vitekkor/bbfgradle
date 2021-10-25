// Original bug: KT-6293

fun main(args: Array<String>) {
    val c: Int? = null
    if (c is Int) {
        val k = object : Runnable{
            override fun run() = Unit
        }
        val g = c + 3 // smart cast should work but error is reported
    }
}
