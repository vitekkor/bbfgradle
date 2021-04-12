// Original bug: KT-15484

data class Foobar(val foo: String = "foo", val bar: String = "bar")

fun main(args: Array<String>) {
    val foobar = Foobar()
    val i = 2

    // won't work
    println(foobar)
    println(i)

    // these however will work
    println("$foobar")
    println("$i")
}
