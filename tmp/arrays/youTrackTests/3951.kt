// Original bug: KT-33685

class Box<T>

@Deprecated("", ReplaceWith("run { bar<E> { } }"))
fun <E : Any, T : Box<E>> Box<T>.foo() = 42

fun <T> bar(f: Box<T>.() -> Unit) = f

fun test(e: Box<Box<Any>>) {
    e.foo()
    
    // replaced with: e.run { bar { } }
    // expected: e.run { bar<Any> { } }
}
