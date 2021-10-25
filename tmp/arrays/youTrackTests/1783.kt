// Original bug: KT-36826

class Foo {
    fun getBar(): String {
        println("do some")
        println("additional actions")
        println("that helps retrieve bar")
        return "bar"
    }
}
