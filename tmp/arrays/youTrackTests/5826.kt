// Original bug: KT-32218

val map: Map<out Any, Any> = mapOf(
	true to true,
	1L to 1L
)
val test = map[1L] //IDE error: "Type inference failed. The value of the type parameter K should be mentioned in input types (argument types, receiver types or expected type). Try to specify it explicitly
