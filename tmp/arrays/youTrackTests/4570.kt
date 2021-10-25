// Original bug: KT-32507

fun foo(bar: Any?): Int {
    bar as String?
    bar ?: throw IllegalStateException()
    return bar.length
}

fun main() {
    println(foo("Hello World"))
}
  