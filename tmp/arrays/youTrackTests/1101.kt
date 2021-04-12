// Original bug: KT-19850


sealed class B
abstract sealed class C // Warning: Modifier 'abstract' is redundant because 'sealed' is present

fun main(args: Array<String>) {
    println(B::class.isAbstract) // false
    println(C::class.isAbstract) // false
}
