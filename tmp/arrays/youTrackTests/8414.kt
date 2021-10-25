// Original bug: KT-3393

fun some0() : Int = 0
fun some1() : Int = 1
fun some2() : Int = 2

fun main(str: Array<String>) {
    val test: Int = (3
    +
    12)

    println(test)

    val some = listOf(1, 2, 3).map { (
        it
        + 1
        + 222222
        * some0()
        * some1()
        * some2()
        ) }

    println(some)

    val other = listOf(1, 2, 3).map {
        it +
        1 +
        22222 *
        some0() *
        some1()
    }

    println(other)
}
