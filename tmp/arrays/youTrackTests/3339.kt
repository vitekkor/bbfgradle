// Original bug: KT-38987

class A {
    fun B.doSomething() {
    }

    fun doSomething() {
    }
}
class B {
    fun run(a: A) {
        with(this) {
            with(a) {
                doSomething()
            }
        }
    }
}
