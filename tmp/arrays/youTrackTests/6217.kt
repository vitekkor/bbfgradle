// Original bug: KT-30604

class Test {
    lateinit var classWithFunction: ClassWithFunction

    val standardLambda1: () -> Unit = { doSth1() }
    val referencedLambda1: () -> Unit = ::doSth1

    val standardLambda2: () -> Unit = { classWithFunction.doSth2() }
    val referencedLambda2_1: () -> Unit = classWithFunction::doSth2 // an error occurred because holds reference to the classWithFunction
    val referencedLambda2_2: () -> Unit = ::classWithFunction.get()::doSth2 // even this throws an error
    
    fun doSth1() {}

    class ClassWithFunction {
        fun doSth2() {}
    }
}
