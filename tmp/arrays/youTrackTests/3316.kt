// Original bug: KT-39137

sealed class Base {
    class Wrap<WTF>(val what: Intermediate) : Base()
    //        ^^^^^ crucial part.
    // Without a type parameter everything works as expected
    sealed class Intermediate : Base() {
        class A : Intermediate()
        class B : Intermediate()
    }
}
fun handle(b: Base) {
    when (if (b is Base.Wrap<*>) b.what else (b as Base.Intermediate)) {
        is Base.Wrap<*> -> TODO()
        // ^^^^^^^^^^^^ subject cannot be Wrap at this point,
        // I expect a compilation error, but
        // everything compiles when Wrap has a type parameter
        is Base.Intermediate.A -> TODO()
        is Base.Intermediate.B -> TODO()
    }
}
