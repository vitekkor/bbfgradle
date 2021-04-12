// Original bug: KT-31514

interface Inner
interface Wrapper<T : Inner>

object Start : Inner
object End : Inner
object Other : Inner

fun <T : Inner> wrapper() = object : Wrapper<T> {}
fun <T : Inner> consume(wrapper: Wrapper<T>) {}

fun error() {
    val list = listOf(wrapper<Start>(), wrapper<End>())
    consume(list.firstOrNull() ?: wrapper<Other>())
}
