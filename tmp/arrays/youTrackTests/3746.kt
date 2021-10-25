// Original bug: KT-36819

fun <K> select(vararg x: K) = x[0]
interface A
class B: A
class C: A
fun <T> id(x: T) = x
class Out<out R>(x: R)
fun main() {
    val x1 = select(id { B() }, id { C() }) // [TYPE_MISMATCH] Type mismatch. Required: () â B; Found: () â C, OK in OI
    val x2 = select({ B() }, { C() }) // OK, CST = () -> A
    val x3 = select(id(Out(B())), id(Out(C()))) // OK, CST = Out<A>
}
