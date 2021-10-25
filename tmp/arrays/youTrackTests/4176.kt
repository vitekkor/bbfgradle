// Original bug: KT-35514

open class Foo

class Valid : Foo()
class Empty : Foo()

fun doSomething(): Test<Foo> {
    return Test<Valid>().func { if (true) Empty() else Valid() }
}

class Test<out T>
fun <R> Test<R>.func(f: () -> R) = this
