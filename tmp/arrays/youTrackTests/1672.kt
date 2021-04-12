// Original bug: KT-11571

interface IBase {
    fun foo() {}
}

class CDerived : IBase {
    // DECLARATION_CANT_BE_INLINED, red code begins
    override inline fun foo() {
        super.foo()
    }
    // red code ends
}
