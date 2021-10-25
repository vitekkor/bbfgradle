// Original bug: KT-42635

inline class Wrapper(val value: Long)

fun main(args: Array<String>) {
    val list = ArrayList<Wrapper>()
    list.add(Wrapper(1))
    for ((_, value) in list.withIndex()) {
    }
}
