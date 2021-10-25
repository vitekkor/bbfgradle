// Original bug: KT-3573

class Data
fun newInit(f: Data.() -> Data) = Data().f()

class TestClass {
    val test: Data = newInit()  { this }
}

fun main(args: Array<String>) {
    TestClass()
}
