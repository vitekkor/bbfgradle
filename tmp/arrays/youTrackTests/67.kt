// Original bug: KT-44749

class Context

fun main() {

    val sc = Context()
    val foo = "123"


    sc.apply {
        sequence<String> {
            println(foo)
            println(this@apply)
            println(this)
        }.take(10).toList()
    }
}
