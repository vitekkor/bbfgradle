// Original bug: KT-33054

sealed class OptionLocal<A> {
    inline fun map(): OptionLocal<A> =
        when (this) {
            is NoneLocal -> this
            is SomeLocal -> SomeLocal(t)
        }
}
class SomeLocal<T>(val t: T) : OptionLocal<T>()
object NoneLocal : OptionLocal<Nothing>()
fun <T> OptionLocal<T>.getOrElse(default: () -> T): T = default()

fun main() {
    val some20 = SomeLocal(10)
    val some22 = some20.map()
    some22.getOrElse { 0 }
}
