// Original bug: KT-18207

fun foo() {
    var b : Any = 0
    fun bar() {
        b = 42
        println(b)  //Error:(7, 17) Kotlin: Smart cast to 'Int' is impossible, because 'b' is a local variable that is captured by a changing closure
    }
}
