// Original bug: KT-7469

fun <T> shuffle(x : List<T>) : List<T> = null!!
fun foo(x: List<String>, f: (List<String>) -> List<String>): Int = null!!
fun bar(x : List<String>) {
    foo(x, { shuffle(it) })
    foo(x, ::shuffle)
}
