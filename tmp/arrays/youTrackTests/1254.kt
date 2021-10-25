// Original bug: KT-43513

package test

class C(val s: String)

class D

class Test(val c: C) {
    fun foo(d: D) {
        val cs = c.s
        d.run {
            println(cs)
        }
    }
}
