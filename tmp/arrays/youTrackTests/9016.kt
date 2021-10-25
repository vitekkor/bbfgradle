// Original bug: KT-12276

open class Bar

abstract class Foo {
    abstract val bar: Bar

    fun foo() {
        baz(bar!!) // Warning "Unnecessary non-null assertion"
        baz(getBarX()!!) // No warning here
    }

    fun baz(bar: Bar) {
    }
}

inline fun <reified T: Bar> Foo.getBarX() = bar as T
