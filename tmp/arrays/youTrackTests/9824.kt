// Original bug: KT-9548

interface A {
    fun <T : Comparable<T>> foo(y: List<T>)
}

interface B {
    fun <T : Comparable<T>> foo(y: List<T>)
}

fun <S> bar(z: S, y: List<String>) where S : A, S : B {
    // Cannot choose among the following candidates without completing type inference: 
    //    public abstract fun <T : kotlin.Comparable<kotlin.String>> foo(y: kotlin.List<kotlin.String>): kotlin.Unit defined in A
    //    public abstract fun <T : kotlin.Comparable<kotlin.String>> foo(y: kotlin.List<kotlin.String>): kotlin.Unit defined in B
    z.foo(y)
}
