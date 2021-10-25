// Original bug: KT-44736


abstract class AbstractClass(){
    protected open val testString =  "AB"
    
    init{
        println("AbstractClass: $testString")
    }
    
    fun printString(){
        println("AbstractClass.printstring(): $testString")
    }
}

class ImplClass : AbstractClass(){    
        init{
        println("ImplClass: $testString")
    }
}


fun main() {
    val implClass = ImplClass()
    implClass.printString()
}
