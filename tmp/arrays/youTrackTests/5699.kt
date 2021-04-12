// Original bug: KT-7726

fun main(args: Array<String>) {
    foo(x = 5)
}

fun foo(f : () -> Unit = { println(x) }, x : Int) { // False warning "Parameter x is never used"
    f()
}
