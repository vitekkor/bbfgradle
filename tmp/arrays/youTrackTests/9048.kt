// Original bug: KT-16939

interface IFoo1<S1, T1> {
    fun foo(s: S1): T1
}

interface IFoo2<S2, T2> {
    fun foo(s: S2): Collection<T2>
}

interface X
interface Y

interface IFoo12<T> : IFoo1<X, T>, IFoo2<Y, T>
// ^ Error:(14, 11) Kotlin: JavaScript name foo_11rb$ is generated for different inherited members: fun foo(s: X): T and fun foo(s: Y): Collection<T>
