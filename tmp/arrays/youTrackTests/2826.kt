// Original bug: KT-37280

interface Out<out T>
class Inv<T> : Out<T> {
    fun replace(e: T) {}
}

fun <T> Out<T>.toInv(): Inv<T> = this as Inv<T>

fun test(o: Out<Number>) {
    o as Out<String> // unchecked cast

    val inv = o.toInv<String>() // OK
    inv.replace("f")
}
