// Original bug: KT-3484

package multiplier

fun parseInt(str: String): Int? {
    try{
        return  Integer.parseInt(str)
    } catch (e: NumberFormatException) {
        println("One of the arguments is not Int")
    }
    return null
}

fun main(args: Array<String>) {
    val x = parseInt("1")
    val y = parseInt("a")

    print((if (x != null) x else 1) * (if (y != null) y else 1))
}
