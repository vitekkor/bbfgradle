// Original bug: KT-37887

class Inv<T>

fun <K> takeTwoInv(x: Inv<K>, y: Inv<K>) {}

fun foo(x: Any, y: Inv<String>) {
    x as Inv<out CharSequence>
    x as Inv<String>
    takeTwoInv(x, y) // required `String`, Found `CharSequence`
}
