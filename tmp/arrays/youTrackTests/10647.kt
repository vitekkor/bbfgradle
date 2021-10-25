// Original bug: KT-1873

fun <T> T.foo() = 2

fun foo() = 1

fun test() {

    val f : () -> Unit = { "".foo() } //'None applicable' error

    val g : () -> Int = { "".foo() } //ok
}
