// Original bug: KT-45086

import java.awt.event.ActionEvent
import java.awt.event.ActionListener

fun foo() {
    if (true) return

    var counter = 0

    val value = object : ActionListener {
        override fun actionPerformed(e: ActionEvent?) {
            counter++
        }
    }
}
