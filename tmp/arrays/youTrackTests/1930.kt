// Original bug: KT-31401

package de.roland_illig.cr

import javax.swing.SwingUtilities

class LaterOnce(private val action: () -> Unit) {

    private var isQueued = false

    fun enqueue() {
        if (!isQueued) {
            isQueued = true
            SwingUtilities.invokeLater(this::dequeue)
        }
    }

    private fun dequeue() {
        isQueued = false
        action()
    }

}
