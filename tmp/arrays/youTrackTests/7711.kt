// Original bug: KT-26042

class Foo(val f: Foo, val a: Int) {
    fun bar(): Pair<Int, Foo> = 0 to this

    fun quux() {
        bar().let { (newA, newF) -> Foo(newF, newA) }
    }
}
