// Original bug: KT-19038

private interface Bag<T> {
	operator fun contains(element: T): Boolean
}

private interface IntBag : Bag<Int> {
	override fun contains(element: Int): Boolean
}

private fun testIntBag(intBag: IntBag) = 1 in intBag
