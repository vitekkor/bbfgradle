// Original bug: KT-1439

class MyClass(var fnc : () -> String) {

    fun test() {
        println(fnc())
    }

}

fun printtest() : String {
    return "test"
}

fun main(args : Array<String>) {
    var c = MyClass({ printtest() })

    c.test()
}
