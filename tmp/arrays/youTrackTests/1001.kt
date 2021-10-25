// Original bug: KT-45380

open class  Foo {

    open fun bar(): String {
        return "OK"
    }
}

class FooClass : Foo() {
    inline  fun bar2(): String {
        return object {
            fun run()= super@FooClass.bar() ////access$bar$s70822 vs access$bar$s414358642
        }.run()
    }
}

fun box(): String {
    return FooClass().bar()
}
