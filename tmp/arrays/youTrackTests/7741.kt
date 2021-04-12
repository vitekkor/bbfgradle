// Original bug: KT-25037

interface A {
    // foo(kotlin.coroutines.experimental.Continuation<? super java.lang.Object>)
    suspend fun foo(): Any?
}

interface B : A {
    // foo(kotlin.coroutines.experimental.Continuation<? super java.lang.String>)
    // is not compatible with A::foo
    override suspend fun foo(): String
}

