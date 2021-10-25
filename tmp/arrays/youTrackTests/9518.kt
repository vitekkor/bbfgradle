// Original bug: KT-12952

fun main(args: Array<String>) {
    arrayOf(1, 2, 3).map { num ->
        if (num == 0) { 
            0
        } else {
            num
        }
    }
}
