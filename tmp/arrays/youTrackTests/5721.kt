// Original bug: KT-29994

fun main() {
    val additionalContextVariable = System.currentTimeMillis()
    val fix = "demo"?.let {
        object : Runnable {
            override fun run() {
                println(additionalContextVariable)
            }
        }
    }
}
