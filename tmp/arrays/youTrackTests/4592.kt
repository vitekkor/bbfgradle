// Original bug: KT-34404

fun main() {
    wrongProviderSelected(true, Byte.MAX_VALUE, "simple text")
} 
fun wrongProviderSelected(bool: Boolean, byte: Byte, str: String) {
    println() //breakpoint here
}
