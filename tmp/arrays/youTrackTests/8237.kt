// Original bug: KT-8608

package cg

import java.util.concurrent.Callable

class A {
    fun f() {
        listOf(1, 2, 3).forEach {
            accept(object : Callable<Unit> {
                override fun call() {
                    if (Math.random() > 1) {
                        println("ok")
                    } else {
                        try {
                            x()
                        } catch(t: Throwable) {
                        }
                    }
                }
            })
        }
    }

    private fun x() {}
}

fun accept(runnable: Callable<*>) {
    runnable.call()
}
