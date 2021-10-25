// Original bug: KT-30151

class A<T>
class B<T>(val t: T, vararg val tX: T)
fun <T> A<T>.foo(b: B<T>){}
fun test(){
    A<Number>().foo(B(1, 1.0))          // B<Any> given but B<Number> expected
    A<Number>().foo(B<Number>(1, 1.0))  // ok
}
