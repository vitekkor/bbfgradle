// Original bug: KT-33935

abstract class Base{
    abstract fun f()
}

class A: Base(){
    companion object{
        val INST = A()
    }
    override fun f() { }
}

class B: Base(){
    companion object{
        val INST = B()
    }
    override fun f(){
        A.INST.f()
    }
}
