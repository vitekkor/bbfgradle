// Original bug: KT-40390

interface MyInterface{
    fun myFun(): String
}

class ClassOne : MyInterface {
    //1 usage
    override fun myFun() = "ClassOne"
}

class ClassTwo : MyInterface {
    //1 usage
    override fun myFun() = "ClassTwo"
}

fun main() {
    ClassOne().myFun()
    ClassTwo().myFun()
}
