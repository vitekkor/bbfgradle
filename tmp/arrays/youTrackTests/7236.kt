// Original bug: KT-25664

fun foo(): UInt = 0xffff_ffffu

fun main(args: Array<String>) {
    println(foo()) // Prints 4294967295
    println(::foo.call()) // Prints -1 -- WRONG!!!
}
