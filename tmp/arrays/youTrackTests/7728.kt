// Original bug: KT-25792

inline class MyInline(val n: Int)

fun main(args: Array<String>) {
    val list = listOf(MyInline(24))
    println("list: $list, contains: ${list.contains(MyInline(24))}")
}
