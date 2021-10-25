// Original bug: KT-22552

/**
 * Try to resolve `param` from multiple locations, priority order (first set value wins):
 *  * function argument
 *  * System property
 *  * default fallback is true
 */
fun f(param: Boolean? = null): Boolean = listOf(
		param, // Boolean?
		System.getProperty("param")?.toBoolean(), // Boolean?
		true // Boolean
).find { it != null }!!
