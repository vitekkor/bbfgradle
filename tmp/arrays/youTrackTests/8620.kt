// Original bug: KT-12316

interface A<T>
interface B
interface C: A<Int>, B

fun <T> foo(a: A<T>) {}
fun foo(x: B) {}

fun bar(x: C) {
    foo(x) // resolved to foo(B). Expected: ambiguity
}
