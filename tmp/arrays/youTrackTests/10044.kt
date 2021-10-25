// Original bug: KT-9855

class MyString(var content : String)

object Greeter {
    fun sayHello(name : String): String {
        var result = MyString(name)

        result = result.prefixWith("Hello ") // this works
        println(result.content)

        result = result + "!" // this also (I have no idea why)
        println(result.content)

        Thread.sleep(50)
        result += "!" // this throws IllegalAccessError

        return result.content
    }
}

private fun MyString.prefixWith(prefix: String) : MyString = MyString("$prefix${this.content}")
private operator fun MyString.plus(suffix: String) : MyString =  MyString("${this.content}$suffix")
//private operator fun MyString.plusAssign(suffix: String) : Unit { this.content = "${this.content}$suffix" }

fun main(vararg args : String) {
    val result = Greeter.sayHello("World")
    println(result)
}
