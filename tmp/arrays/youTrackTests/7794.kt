// Original bug: KT-25549

fun <T> String.parse(): List<T> =
    this.split(",").map {
        try {
            it as T
        } catch (e: ClassCastException) {
            error("fail") // <-- 1
        }
    }

fun main(args: Array<String>) {
    val list = "a,b,c".parse<Int>()
    println(list)
    list.forEach {
        try {
            it + 1
        } catch (e: ClassCastException) {
            error("epic fail") // <-- 2
        }
    }
}
