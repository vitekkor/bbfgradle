// Original bug: KT-28604

class A<in T>(val v: @UnsafeVariance T)

fun <T> foo(a: A<T>, b: A<T>): A<T> = if (true) a else b

fun test() {
   val wat = foo(A(42), A("forty two"))
   // A<Int>

   val dis = foo(A(42), A<String>("forty two"))
   // A<String>

   val zis = foo(A<Byte>(42), A("forty two"))
   // A<Byte>

   val sin = foo(A<Byte>(42), A<String>("forty two"))
   // A<Byte & String>

   val hailTheKing = A(42) ?: A("forty two")
   // A<Int & String>
}
