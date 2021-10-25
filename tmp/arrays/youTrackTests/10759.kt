// Original bug: KT-1918

import javax.swing.JFrame
import javax.swing.JPanel
import java.awt.Graphics2D
import java.awt.Graphics

fun mainFrame(title : String, init : JFrame.() -> Unit) : JFrame {
    val frame = JFrame(title)
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
    frame.init()
    return frame
}

fun drawPanel(paint : Graphics2D.() -> Unit) : JPanel {
    return object : JPanel() {

        protected override fun paintComponent(g : Graphics?) {
            super<JPanel>.paintComponent(g)
            (g as? Graphics2D)?.paint()
        }
    }
}
