// Original bug: KT-17175

fun main(args: Array<String>) {
    Foo()    
}
class Foo {
    init {
        println(this::blah == this::blah) // Expected: true, Actual: Jvm backend: true, Js: false
    }   
    private fun blah() {
    }
}

