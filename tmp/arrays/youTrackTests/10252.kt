// Original bug: KT-7800

fun main(args: Array<String>) {
    val x: Int = 1.let {
        val value: Int? = null
        if (value == null) {
            return@let 1
        }
        else {
            value   // works here
        }
    }
}
