// Original bug: KT-9548

interface A {
    fun foo(y: List<String>)
}

interface B {
    fun foo(y: List<String>)
}

fun <S> bar(z: S, y: List<String>) where S : A, S : B {
    // Overload resolution ambiguity: 
    //    public abstract fun foo(y: kotlin.List<kotlin.String>): kotlin.Unit defined in A
    //    public abstract fun foo(y: kotlin.List<kotlin.String>): kotlin.Unit defined in B
    z.foo(y)
}
