// Original bug: KT-44736

import kotlin.system.*

abstract class AbstractClass(){
    protected open val testString = "AB"
    
    init{
        println("AbstractClass: $testString")
    }
    
    fun printString(){
        println("AbstractClass.printstring(): $testString")
    }
}

class ImplClass : AbstractClass(){
    override val testString = "BC"
    
        init{
        println("ImplClass: $testString")
    }
}


fun main() {
    val implClass = ImplClass()
    implClass.printString()
}
