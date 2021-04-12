// Original bug: KT-19850

interface I
open interface J // Warning: Modifier 'open' is redundant for 'interface'

fun main(args: Array<String>) {
    println(I::class.isOpen) // false
    println(J::class.isOpen) // false
} 
