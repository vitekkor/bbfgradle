// Original bug: KT-15751

fun main(args: Array<String>) {
    A().foo {}
}

class A {
    val foo = fun(call: () -> Unit) =
            ext {
                fun send() {
                    call()
                }

                bar {
                    send()
                }
            }

    fun bar(body: () -> Unit) {

    }

    inline fun A.ext(init: X.() -> Unit) {
        return X().init()
    }

    class X
}
