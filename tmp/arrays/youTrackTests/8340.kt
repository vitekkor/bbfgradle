// Original bug: KT-6884

interface EventHandler {
	fun onEvent1()
	fun onEvent2()
	fun onEvent3()
}

interface UIElement {
	fun addEventHandler(eventHandler: EventHandler)
}

inline fun UIElement.onEvent(
	crossinline onEvent1: () -> Unit = {},
	crossinline onEvent2: () -> Unit = {},
	crossinline onEvent3: () -> Unit = {}
) = addEventHandler(object : EventHandler {
	override fun onEvent1() = onEvent1()
	override fun onEvent2() = onEvent2()
	override fun onEvent3() = onEvent3()
})

fun addUILogic(uiElement: UIElement) {
	uiElement.onEvent(
		onEvent2 = { println("event 2") },
		onEvent3 = { println("event 3") }
	)
}
