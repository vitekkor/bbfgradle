// Original bug: KT-34809

open class K

class Foo {
    fun foo(n: Int) {
        val x = object : K() { // "Convert object literal to class" with target "class" or "file"
        }
    }
}
