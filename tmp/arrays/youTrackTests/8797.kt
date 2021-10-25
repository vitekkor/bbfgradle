// Original bug: KT-18792

//suspend inline fun <reified T : Scene> changeTo(
// 	vararg injects: Any,
// time: TimeSpan = 0.seconds,
// transition: Transition = AlphaTransition
// ) = changeTo(T::class.java, *injects, time = time, transition = transition)
