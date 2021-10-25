// Original bug: KT-34565

fun main() {
    println("aaa")
    foo(bar = 1)
    foo(1)
}
@AC(p = "aaa")
fun foo(bar: Int) {
    println(bar)
    A(1, "22")
    A(bbb = 1, ccc ="22")
    A(bbb = 1, ccc ="22")
}

@AC(p = "aaa")
class A(val bbb: Int, ccc: String)

annotation class AC(val p: String)
