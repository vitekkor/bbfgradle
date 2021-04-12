// Original bug: KT-43732

import kotlin.reflect.KProperty

fun main() {
    print(t) // INVOKESTATIC getT, not inlined
    Foo.foo() // is inlined
}

object Foo {
    var name = "foo"
    inline operator fun getValue(ref: Any?, p: KProperty<*>) = 1
    inline fun foo() { name += "2" }
}

val t by Foo // backing field is Foo.INSTANCE
