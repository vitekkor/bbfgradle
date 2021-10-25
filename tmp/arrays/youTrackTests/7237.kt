// Original bug: KT-25664

fun foo(x: UInt) {
    println(x)
}

fun main(args: Array<String>) {
    foo(0xffff_ffffu) // Prints 4294967295
    ::foo.call(0xffff_ffffu) // Exception -- WRONG!!!
}
