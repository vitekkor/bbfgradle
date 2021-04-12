// Original bug: KT-33354

val a: String = "This is top-level val"
var b: String = "This is top-level var"

fun usageVariables() {
    val c: String = "This is local val"
    val d: String = "This is local var"

    // breakpoint here
    println(a)
    println(b)
    println(c)
    println(d)
}
