// Original bug: KT-13808

import java.awt.Color
import java.awt.Graphics
import java.util.*
import javax.swing.JFrame
import javax.swing.JPanel
import kotlin.concurrent.thread

fun main(args: Array<String>) {
    MyOverlay.isVisible = true

    thread {
        while (true) {
            myLoop()
            Thread.sleep(1)
        }
    }
}

val myX = 10

fun myLoop() {
    for (i in 0..500) {

        val x = myX.toInt() //Issue with escape analysis (I assume)

        MyOverlay {
            color = Color.RED
            drawString("Hello World", x, 10)
        }

    }
}

object MyOverlay : JFrame() {

    private val hooks = Collections.synchronizedList(ArrayList<Graphics.() -> Unit>(128))

    private val frame = object : JPanel() {
        override fun paintComponent(g: Graphics) {
            for (i in 0..hooks.size - 1) hooks[i](g)
            hooks.clear()
        }
    }

    init {
        setSize(500, 500)

        frame.setBounds(0, 0, 500, 500)
        frame.background = Color.RED
        frame.foreground = Color.RED

        contentPane = frame

        isVisible = true

        thread {
            while (!Thread.interrupted()) {
                MyOverlay.repaint()
                Thread.sleep(16)
            }
        }

    }

    operator fun invoke(body: Graphics.() -> Unit) {
        hooks.add(body)
    }

}