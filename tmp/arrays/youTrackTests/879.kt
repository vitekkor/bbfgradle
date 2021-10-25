// Original bug: KT-45200

/**
 * Builds new string by populating newly created [StringBuilder] using provided [builderAction]
 * and then prints it and the line separator to the standard output stream.
 */
public inline fun println(builderAction: StringBuilder.() -> Unit) {
	println(buildString(builderAction))
}

/**
 * Builds new string by populating newly created [StringBuilder] initialized with the given [capacity]
 * using provided [builderAction] and then prints it and the line separator to the standard output stream.
 */
public inline fun println(capacity: Int, builderAction: StringBuilder.() -> Unit) {
	println(buildString(capacity, builderAction))
}

/**
 * Builds new string by populating newly created [StringBuilder] using provided [builderAction]
 * and then prints it to the standard output stream.
 */
public inline fun print(builderAction: StringBuilder.() -> Unit) {
	print(buildString(builderAction))
}

/**
 * Builds new string by populating newly created [StringBuilder] initialized with the given [capacity]
 * using provided [builderAction] and then prints it to the standard output stream.
 */
public inline fun print(capacity: Int, builderAction: StringBuilder.() -> Unit) {
	print(buildString(capacity, builderAction))
}
