// Original bug: KT-22552

fun f(param: Boolean? = null): Boolean = listOf(
		param, // Boolean?
		System.getProperty("param")?.toBoolean(), // Boolean?
		true // Boolean
).filterNotNull().first()
