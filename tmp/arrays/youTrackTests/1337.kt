// Original bug: KT-36865

data class A(val b: Int, val c: Long, val d: Long)

fun parse(str: String): A {
	val parts = str.split(";")
	return A(parts[0].toInt(), parts[1].toLong(), if (parts.size > 2) parts[2].toLong() else 0L)
}

fun parse(a: String?, b: Int, c: Long, d: Long): A {
	a?.let {
		return parse(it)
	}
	return A(b, c, d)
}
