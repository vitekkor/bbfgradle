// Original bug: KT-31390

interface A {
    val x: A?
    val c: Boolean
}
fun test(x: A?) {
    if (x == null) return
    var y = x!! // warning: unnessary non-null assertion
    while (y.c) y = y.x ?: return
}
