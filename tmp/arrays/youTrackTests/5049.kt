// Original bug: KT-25892

interface MyInterface {
    fun myMethod() : String
}

object MyObject : MyInterface {
    override fun myMethod() : String = "hello"
}

class MyObjectServiceLoaderProxy : MyInterface by MyObject
