// Original bug: KT-38957

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZonedDateTime

class A<T> {}

@JvmName("a")
fun <T: ZonedDateTime> A<T>.foo() = {}
@JvmName("b")
fun <T: LocalDateTime> A<T>.foo() = {}
@JvmName("c")
fun <T: LocalDate> A<T>.foo() = {}

fun test() {
    val a = A<ZonedDateTime>()
    a.foo() // works
    a.foo<ZonedDateTime>() //overload ambiguity
}
