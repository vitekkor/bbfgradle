// Original bug: KT-27616

fun cond(a: Any?) = a == null
fun foo(a: Any, b: Any, c: Any) {}
var o = object {}


fun main() {
    val f = if (cond(o)) {
        foo(o, o, o)
        1  
        { a:Any, b:Any -> a.hashCode() + b.hashCode() }
    } else {
        { _, _ -> o.hashCode() }
    }

    println(f(o, o))
}
