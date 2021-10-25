// Original bug: KT-40273

interface IFoo {
    private class Test
    // JVM_IR:  public final static INNERCLASS test/IFoo$Test test/IFoo Test
    // JVM:     private final static INNERCLASS test/IFoo$Test test/IFoo Test

    fun foo(): Any = bar()
    
    private fun bar() = Test()
}
