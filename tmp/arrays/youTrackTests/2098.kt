// Original bug: KT-18753

open class Base(val x: Int)

fun test() =
        object : Base(42) {
            fun foo(s: String) {
                println("$s $x")
            }
        }
