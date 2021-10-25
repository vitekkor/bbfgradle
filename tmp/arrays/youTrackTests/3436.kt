// Original bug: KT-38722

interface SomeInterface {
    fun interfaceMethod()
}

class SomeImpl : SomeInterface {
    override fun interfaceMethod() {
        TODO("Not yet implemented")
    }
    
    fun classMethod() {
        
    }

}

class Client {

    val a = SomeImpl()

    init {
        a.classMethod()
        a.interfaceMethod()
    }
}
