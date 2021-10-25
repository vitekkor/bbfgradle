// Original bug: KT-43038

package test

import kotlin.reflect.KCallable

annotation class Ann

interface IFoo {
    @Ann val foo: String
    @Ann fun bar()
}

class DFoo(d: IFoo) : IFoo by d

fun testAnnotations(kc: KCallable<*>) {
    println(kc.annotations)
}

fun main() {
    testAnnotations(DFoo::foo) // > []
    testAnnotations(DFoo::bar) // > [@test.Ann()]
}
