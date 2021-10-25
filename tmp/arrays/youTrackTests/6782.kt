// Original bug: KT-8377

fun main(args: Array<String>) {
    val l = listOf(1,2,3,4)
    val someNum = 2
    val a = l.size - 1
            - someNum
            - 1
    println(a)
}
