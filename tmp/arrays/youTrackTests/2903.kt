// Original bug: KT-31996

fun ordinaryFunction(block: () -> Unit) {
    println("hi!")
}
fun foo() {
    ordinaryFunction here@{
        return@here
    }
}
fun main() {
    foo()
}
