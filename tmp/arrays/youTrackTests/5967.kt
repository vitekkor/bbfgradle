// Original bug: KT-19970

interface Interface {
    fun f() {}
}

open class First() {}

class Second : First(), Interface {
    override fun f(){}

    fun superf(){
        super<Interface>.f()
    }
}
