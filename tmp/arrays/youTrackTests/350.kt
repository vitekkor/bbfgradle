// Original bug: KT-31347

fun handle(f: suspend () -> Unit) {}

open class Foo {
    inline fun foo(crossinline body: suspend (Baz) -> Unit, crossinline createContext: () -> Baz) {
        handle {
            body(createContext())
        }
    }
}

class Bar : Foo() {
    inline fun bar(crossinline body: suspend (Baz) -> Unit) {
        this.foo(body) {
            Baz(Unit)
        }
    }
}

class Baz(unit: Unit)
