// Original bug: KT-17159

interface A<T> {
    fun foo(x: T)
}

interface B {
    fun foo(x: Int?)
}

class C : A<Int>, B {
    // Signature: foo(I)
    // bridge: foo(Ljava/lang/Object)
    // should we have another bridge?, namely 'foo(Ljava/lang/Integer;)'
    // The problem with the last is that it accidentally overrides B::foo and it'd a breaking change
    override fun foo(x: Int) {}

    // Signature: foo(Ljava/lang/Integer;)
    override fun foo(x: Int?) {}
}
