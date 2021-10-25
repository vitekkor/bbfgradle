// Original bug: KT-1942

import javax.swing.JFrame
import javax.swing.JPanel
import java.awt.Graphics
import java.awt.BorderLayout
import java.util.Timer
import java.util.TimerTask
import java.awt.Color
import java.awt.Graphics2D
import java.awt.Point

class View() : JPanel() {
    protected override fun paintComponent(g : Graphics?) {
        super<JPanel>.paintComponent(g)
        val g2d = g as Graphics2D
        g2d.setColor(Color.BLUE)
        g2d.fillOval(x, y, 0, 0) // Should not compile
    }
}
