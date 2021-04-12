// Original bug: KT-27896

import MenuEventType.OPTIONS
import MenuEventType.OVERLAY

class MenuEvent(
    @JvmField val type: MenuEventType,
    @JvmField val data: Any
)

enum class MenuEventType {
    OPTIONS,
    OVERLAY
}

fun main(args: Array<String>) {
    val event = MenuEvent(MenuEventType.OPTIONS, "")
    handleMenuEvent(event)
}

fun handleMenuEvent(event: MenuEvent) {
    event.run {
        when (type) {
            OPTIONS -> showOptions(data)
            OVERLAY -> println("overlay")
        }
    }
}

fun showOptions(data: Any) {
    println("options")
}
