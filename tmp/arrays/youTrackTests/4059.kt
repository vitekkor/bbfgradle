// Original bug: KT-22070

import javax.swing.*
import java.awt.event.*
import kotlin.reflect.*

fun <V> wrap(f: KFunction2<*, V, Unit>, v: V): Unit = TODO()

fun test() {
    wrap(JButton::addActionListener, ActionListener {})                  // Type inference failed
    wrap<ActionListener>(JButton::addActionListener, ActionListener {})  // Unnecessary explicit type arg
}
