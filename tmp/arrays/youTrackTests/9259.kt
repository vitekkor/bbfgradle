// Original bug: KT-14140

fun use(x: Int) {
    val method: (Int, String) -> Unit =  when (x) {
        1 -> ::bar
        else -> ::foo
    }
}

fun foo(a: Int, b: String) {}
fun foo(a: Int) {}
fun bar(a: Int, b: String) {}
fun bar(a: Int) {}
