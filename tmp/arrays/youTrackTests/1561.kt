// Original bug: KT-8167

fun String.bar(): String {
    class A(val x: Int) {
        val a = this@bar
    }
    return A(0).a
}
fun main(args: Array<String>) {
    println("String.bar() throws VerifyError".bar())
}
