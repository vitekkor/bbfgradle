// Original bug: KT-30831

class Outer<T>(init: () -> T)
class Inner<E>

var foo = Outer<Inner<Int>> { Inner() }

fun bar() {
    foo = Outer<Inner<Int>> { Inner() } // 'Remove explicit type arguments' inspection
}
