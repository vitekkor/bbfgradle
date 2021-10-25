// Original bug: KT-39289

package test

@Target(AnnotationTarget.EXPRESSION)
@Retention(AnnotationRetention.SOURCE)
annotation class Ann

class C(val x: String)

fun foo() {}
fun bar() = true

fun flag1() = true
fun flag2() = false

fun test(c: C?) {
    @Ann
    if (flag1()) {
        if (flag2()) {
            foo()
        } else {
            c?.let { bar() }
        }
    }
}

fun main() {
    test(C("OK"))
}
