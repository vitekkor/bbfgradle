// Original bug: KT-2837

    fun Foo.test() {
        println("") // kotlin.io.println(), but should be Foo.println()
    }

    fun Foo.println(a: Any) {}
    class Foo {
        
    }
