// Original bug: KT-29949

class A<T : Any> {}

fun <T : Any> A<T>.foo(`null`: String?): A<T> = this
fun <T : Any> A<T>.foo(a: Int): A<T> = this
fun test() {
    A<Int>().foo(null)            // cannot chose among ... without completing type inference => should be clearly the first overload
    A<Int>().foo(null as String?) // compiles
    A<Int>().foo<Int>(null)       // compiles
}
