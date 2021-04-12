// Original bug: KT-34125

package com.michaelschlies.test

import java.lang.ref.WeakReference

fun main(vararg args: String) {
    val main = Main()
    val t = Thread(Main())
    t.run()
    t.join()
}

class Main : Runnable {
    fun randomThing(lambdaLiteral: () -> Any) {
        lambdaLiteral.invoke()
    }

    companion object {
        @JvmStatic
        fun x(): String {
            return ""
        }
    }

    override fun run() {
        class MainTwo {
            var ssid: String? = null
        }

        val someField = "random"
        while (true) {
            randomThing {
                x()
                var v: WeakReference<MainTwo>? = WeakReference(MainTwo().apply { ssid = "asdf" })
                v?.get()?.ssid
                val someLastField = "blagblahblah"
                v = null
                someLastField.length
            };
            { fieldOne: String, fieldTwo: String ->
                someField.length
            }.invoke(someField, someOtherField);
            randomThing {
                this.someOtherField.length
            }
            Thread.sleep(30)
        }
    }
    private val someOtherField = String(StringBuilder("random2"))
}
