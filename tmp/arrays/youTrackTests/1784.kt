// Original bug: KT-36826

class Foo {
    val bar: String
        get() {
            println("do some")
            println("additional actions")
            println("that helps retrieve bar")
            return "bar"
        }
}
