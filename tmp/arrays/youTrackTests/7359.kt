// Original bug: KT-22552

fun f(param: Boolean? = null): Boolean = listOfNotNull(
		param, // Boolean?
		System.getProperty("param")?.toBoolean(), // Boolean?
		true // Boolean
).first()
