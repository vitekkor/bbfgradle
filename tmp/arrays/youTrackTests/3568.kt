// Original bug: KT-38599

class OperatorBug {
    fun setValue(k: String, v: Int) = println("$k: $v")
}

operator fun OperatorBug.set(k: String, arg: Char = 'd', v: Int) {
    when (arg) {
        'd' -> setValue(k, v)
        else -> println("Got non-default value for 'arg': $arg")
    }
}

fun main(args:Array<String>) {
    val ob = OperatorBug()
    ob["foo"] = 6
}
