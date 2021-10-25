// Original bug: KT-42932

fun main() {
    println("123".indexOfAny(if (true) {
        val array = CharArray(1000000000)
        for (i in array.indices)
            array[i] = '1'
        array
    } else CharArray(0)))
}
