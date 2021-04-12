// Original bug: KT-16717

fun main(args: Array<String>) {
 val pad = Pad(1f, 1f, 1f, 1f).copy() // Unhandled JavaScript Exception
}

data class Pad(
		var top: Float,
		var right: Float,
		var bottom: Float,
		var left: Float) {
	constructor() : this(0f, 0f, 0f, 0f)
	constructor(all: Float) : this(all, all, all, all)
}
