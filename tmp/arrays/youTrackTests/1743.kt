// Original bug: KT-11732

interface A<T : Number> {
    val foo: T

    fun doStuff(bar: T = foo) = 4
}

class B : A<Int> {
    override val foo: Int get() = 7

    override fun doStuff(bar: Int) = super.doStuff(bar)
}

fun main(args: Array<String>) {
    B().doStuff() 
    // [new B]
    // ICONST_0 <-- expected: Ljava/lang/Number;
    // ICONST_1
    // ACONST_NULL
    // INVOKESTATIC A$DefaultImpls.doStuff$default (Lktws/ws/A;Ljava/lang/Number;ILjava/lang/Object;)I
}
