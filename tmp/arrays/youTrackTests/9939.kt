// Original bug: KT-8552

interface T {
    fun foo() {}
}
class T1 : T
class T2 : T

class A(var state: T) {
    fun foo() {
        state = T1()

        for (i in 0..10) {
            state.foo()   // <-- bad resolved call

            if (i == 5) {
                changeState()
            }
        }
    }

    fun changeState() {
        state = T2()
    }
}

fun box(): String {
    A(T1()).foo()
    return "OK"
}
