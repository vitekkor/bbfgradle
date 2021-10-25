// Original bug: KT-39229

fun interface Foo<T> {
    fun invoke(x: T)
}

/* 1 */ fun <T> foo(x: (Int) -> T) { }

/* 2 */ fun foo(block: Foo<Int>) { }

fun main() {
    foo { } // invoke (2) in NI, invoke (1) in OI
}
