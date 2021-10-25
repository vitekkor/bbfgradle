// Original bug: KT-8704

interface A<T>

interface B<T> {
    val name: String
}

fun <T> A<T>.getB(): B<T> = null!!



fun foo1(f: A<*>): String {
    return f.getB().name   // error
}

fun foo2(f: A<*>): String {
    val g: B<*> = f.getB()
    return g.name          // ok
}
