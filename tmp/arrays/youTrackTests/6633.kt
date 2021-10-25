// Original bug: KT-27116

import java.util.*

val r = Random()

val runMe = object : Runnable {
    override fun run() {
        if (r.nextBoolean()) return
        if (r.nextBoolean()) return
        if (r.nextBoolean()) return
        println("Still running")
    }
}
