// Original bug: KT-37227

import javax.swing.JSeparator
import javax.swing.plaf.SeparatorUI

class SimpleSeparatorUI : SeparatorUI()

fun main() {
    val separator = JSeparator()
    separator.setUI(SimpleSeparatorUI()) // OK
    separator.ui = SimpleSeparatorUI() // Val cannot be reassigned
}
