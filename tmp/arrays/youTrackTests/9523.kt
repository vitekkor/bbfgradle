// Original bug: KT-12324

interface SomeInterface {
 fun a()
}

class A : SomeInterface {
 override fun a() {}
}

class B(private val s: SomeInterface) {
 fun callA() {
   s.a()
 }
}