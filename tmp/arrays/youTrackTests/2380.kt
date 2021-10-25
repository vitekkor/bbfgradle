// Original bug: KT-40960

package library;

open class A(n: Int) {
    constructor(): this(1)

    open class T(n: Int) {
        constructor(): this(1)

        fun bar(b: Int): Int = b
    }

    fun foo(a: Int): Int = a

    companion object {

    }
}
