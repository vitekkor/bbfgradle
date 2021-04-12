// Original bug: KT-9551

fun main(args: Array<String>) {
    val x : Any = ""
    val y = x as Any? // False warning "No cast needed"
    val z = foo(y).toUpperCase()
}

fun foo(y : Any){}
fun foo(y : Any?, z : String = "") : String = z
