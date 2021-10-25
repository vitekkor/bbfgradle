// Original bug: KT-33319

fun main(args: Array<String>) {
    println(ButtonState.DOWN)
}

enum class ButtonState(
		val isUp: Boolean = false,
		val isOver: Boolean = false,
		val isDown: Boolean = false,
		val isToggled: Boolean = false,
		val isIndeterminate: Boolean = false,
		val fallback: ButtonState? = null
) {
	UP(isUp = true, fallback = null),
	OVER(isOver = true, fallback = UP),
	DOWN(isDown = true, isOver = true, fallback = OVER),
	TOGGLED_UP(isUp = true, isToggled = true, fallback = UP),
	TOGGLED_OVER(isOver = true, isToggled = true, fallback = TOGGLED_UP),
	TOGGLED_DOWN(isDown = true, isOver = true, isToggled = true, fallback = TOGGLED_UP),
	INDETERMINATE_UP(isUp = true, isIndeterminate = true, fallback = UP),
	INDETERMINATE_OVER(isOver = true, isIndeterminate = true, fallback = INDETERMINATE_UP),
	INDETERMINATE_DOWN(isDown = true, isOver = true, isIndeterminate = true, fallback = INDETERMINATE_UP),
	DISABLED(fallback = UP);
}
