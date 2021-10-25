// Original bug: KT-13915

data class Data1(val x: Any)
data class Data2(val x: IntArray)

fun main(args: Array<String>) {
    println(Data1(intArrayOf(1, 2, 3))) // Data1(x=[I@5e481248)
    println(Data2(intArrayOf(1, 2, 3))) // Data2(x=[1, 2, 3])
}
