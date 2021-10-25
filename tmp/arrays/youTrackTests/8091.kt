// Original bug: KT-1999

package ball

import javax.swing.JFrame
import javax.swing.JLabel
import java.io.File
import java.awt.Font
import javax.swing.JPanel
import java.awt.Graphics
import java.util.Timer
import java.util.TimerTask
import java.awt.Color

class MovingBall(var x : Int, var y : Int, var radius : Int) {
    var vx : Int = 1
    var vy : Int = 1
}

class MyPanel(val ball : MovingBall) : JPanel() {

    protected override fun paintComponent(g : Graphics?) {
        super<JPanel>.paintComponent(g)

        if (g == null) return

        g.setColor(Color.BLUE)
        g.fillOval(ball.x - ball.radius,
                   ball.y - ball.radius,
                   ball.radius * 2, ball.radius * 2)
    }
}

fun main(args : Array<String>) {
    val frame = JFrame("Files")
    frame.setSize(600, 600)

    val ball = MovingBall(x = 50, y = 50, radius = 50)
    frame.add(MyPanel(ball))

    frame.setVisible(true)

    task {
        ball.x += ball.vx
        ball.y += ball.vy

        frame.repaint() // A breakpoint here doesn't work
    }.schedule(
        period = 3
    )

}

fun TimerTask.schedule(timer : Timer = Timer(), delay : Long = 0, period : Long) {
    timer.schedule(this, delay, period)
}

fun task(task : () -> Unit) : TimerTask =
    object : TimerTask() {
        public override fun run() {
            task()
        }
    }
