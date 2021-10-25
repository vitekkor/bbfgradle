// Original bug: KT-32384

inline class A(val x: Int)

fun <T : A> cast(origin: Any): T? = origin as? T // java.lang.NullPointerException
fun main() {
    val castFun = cast<A>(0)
}
