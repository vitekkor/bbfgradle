// Original bug: KT-18422

val foo = Any()
fun bar() {
    var a: Int? = null
    synchronized(foo) {
        a = foo.hashCode()
    }
    if (a != null) {
        println(a) // Compile error: Smart cast to 'Int' is impossible, because 'a' is a local variable that is captured by a changing closure
    }
}
