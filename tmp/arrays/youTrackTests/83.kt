// Original bug: KT-10468

interface Appendable {
    fun append(text: String)
    fun appendLine()
}

fun Appendable.appendLine(text: String) {
    append(text)
    appendLine()
}

fun writeFile(block: Appendable.() -> Unit) {
    // Omitted code
}

fun usage() {
    writeFile { 
        appendLine("Some text") // <--- The Appendable instance is in scope
    }
}
