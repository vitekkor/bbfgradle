// Original bug: KT-9582

public open class A {
    protected open class B
}

public open class C : A() {
    protected open class D {
        internal open class E : A.B() // False warning: subclass effective visibility 'protected & internal(C)' should be the same or less permissive than its superclass effective visibility 'protected(A)'
    }
}
