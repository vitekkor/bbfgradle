// Original bug: KT-19502

    fun foo() {
        println("foo") // works fine
    }

    fun foo2() : Unit {
        println("foo") // works also fine
    }

    fun bar() : Any {
        println("foo")
        return Unit // required, compile error if left out
    }
