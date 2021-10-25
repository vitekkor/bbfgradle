// Original bug: KT-11314

abstract class Table<T>(
    val content: Array<Array<T>>
)

fun main(args: Array<String>) {
    object : Table<Int>(
        Array(10, {
            x-> Array(10, {y -> 3})
        })
    ) {}
}
