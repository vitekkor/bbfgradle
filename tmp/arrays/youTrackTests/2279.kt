// Original bug: KT-41921

val name: String = "Lewis"

fun printMessage(message: String) {
    println(message)
}

fun doThings() {
    printMessage(
        //language=JSON
        """
        {
            "message": "Hello $name"
        }
        """.trimIndent())
}
