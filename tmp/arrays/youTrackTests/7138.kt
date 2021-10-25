// Original bug: KT-28670

interface A
interface B { fun test() {} }

fun <K> select(vararg x: K): K = x[0]

fun test(a: A?, b: B?) {
    b as A?
    a as B?
    val c = select(a, b) // c is {B? & A?}
    if (c != null)
        c.test() // c is still {B? & A?}, unsafe call
}
