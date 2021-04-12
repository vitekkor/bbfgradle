// Original bug: KT-29692

fun main() {
    val posZero = 0.0
    val negZero = -0.0
    if (posZero == negZero) //Inspection reports 'always false', but actually in IEEE754 it's always TRUE
        println("EQUAL")
    else
        println("NOT EQUAL")

}
