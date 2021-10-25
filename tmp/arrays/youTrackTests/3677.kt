// Original bug: KT-32349

val x = foo()
fun foo() {
    notYetInitialized // val is null due to initialization order, but static analyzer sees 'Nothing' and thinks control flow ends up here
    println("Static analyzer thinks that this code is unreachable, but you will see it in output anyway") // [UNREACHABLE_CODE] Unreachable code
}
val notYetInitialized: Nothing = throw Exception()

fun main() {}
