// Original bug: KT-16844

class A
class B
class C

fun <F, T> handle(x: (F) -> T): T = throw NotImplementedError()

val A.p get() = 1
val B.p get() = handle(A::p)

val C.p get() = B().p
