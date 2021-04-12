// Original bug: KT-40956

import org.jetbrains.annotations.Nls

interface RowBuilder

open class Cell
data class Row(val title: String) : Cell(), RowBuilder
class InnerCell : Cell(), RowBuilder

fun titledRow(@Nls title: String, init: Row.() -> Unit): Row {
    return Row(title).apply(init)
}

fun Cell.checkBox(ui: String) {
    println("$this.checkBox($ui)")
}

private fun RowBuilder.twoColumnRow(column1: InnerCell.() -> Unit, column2: InnerCell.() -> Unit) {
    InnerCell().apply {
        column1()
        column2()
    }
}

private fun placeholder() {
    println("placeholder")
}

fun main() {
    fun optional(condition: Boolean, control: InnerCell.() -> Unit): (InnerCell.() -> Unit)? =
            if (condition) control else null

    val cdSmoothScrolling = "cdSmoothScrolling"
    val cdDnDWithAlt = "cdDnDWithAlt"
    val cdEnableBorderlessMode = "cdEnableBorderlessMode"
    val cdFullPathsInTitleBar = "cdFullPathsInTitleBar"
    val cdShowMenuIcons = "cdShowMenuIcons"

    titledRow("group.ui.options") {
        val rightColumnControls = sequenceOf<(InnerCell.() -> Unit)?>(
                {
                    checkBox(cdSmoothScrolling)
                },
                { checkBox(cdDnDWithAlt) },
                optional(true) {
                    checkBox(cdEnableBorderlessMode)
                },
                { checkBox(cdFullPathsInTitleBar) },
                { checkBox(cdShowMenuIcons) }
        ).filterNotNull()
        val rightIt = rightColumnControls.iterator()
        while (rightIt.hasNext()) {
            when {
                rightIt.hasNext() -> twoColumnRow(rightIt.next()) { placeholder() } // move from right to left
            }
        }
    }
}
