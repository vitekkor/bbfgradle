// Original bug: KT-23869

fun foo2() {
    val test = Test()
    foo(test::bar)
}

private fun foo(method: (Int) -> Unit) {
    method.invoke(1)
}
class Test  {
    fun bar(num: Int) {

    }
}
