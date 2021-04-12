// Original bug: KT-1759

package hello.hi

class Greeter(var name : String) {
    fun greet() {
        name = name.plus("")
        println("Hello, $name");
    }
}

fun main(args : Array<String>) {
    Greeter(args[0]).greet()
}
