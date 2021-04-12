// Original bug: KT-24376

@DslMarker
annotation class DslMark

@DslMark
class A {
    fun funInA() {}
    fun B.extFunInB() = B::funInB
}

@DslMark
class B {
    fun funInB() {}
}

fun main(args: Array<String>) {
    A().apply inA@{
        B().apply inB@{
            //funInA()             // Fails (Can't be called in this context)
            //this.funInA()        // Fails (Doesn't exists)
            this@inA.funInA()      // Works
            //this@inB.funInA()    // Fails (Doesn't exists)
            funInB()               // Works
            this.funInB()          // Works
            //this@inA.funInB()    // Fails (Doesn't exists)
            this@inB.funInB()      // Works
            //extFunInB()          // Fails (Can't be called in this context)
            //this.extFunInB()     // Fails (Can't be called in this context)
            //this@inA.extFunInB() // Fails (Doesn't exists)
            //this@inB.extFunInB() // Fails (Can't be called in this context)
        }
    }
}
