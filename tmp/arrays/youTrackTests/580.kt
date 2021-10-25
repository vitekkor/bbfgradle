// Original bug: KT-42712

import kotlin.math.roundToInt

fun main() {
	var totalExact = 0.0
	var totalTrim = 0.0

	testExact(if ((0..1).random() == 0) Any() else null, if ((0..1).random() == 0) Any() else null, arrayOfNulls(8))

	repeat(10_000_000) {
		val arg1 = if ((0..1).random() == 0) Any() else null
		val arg2 = if ((0..1).random() == 0) Any() else null
		val items = arrayOfNulls<Any>((2..30).random())
		totalExact += testExact(arg1, arg2, items)

		/*if (it != 0 && it % 10_000 == 0)
			println("$it iteration")*/
	}
	System.gc()
	testTrim(if ((0..1).random() == 0) Any() else null, if ((0..1).random() == 0) Any() else null, arrayOfNulls(8))
	repeat(10_000_000) {
		val arg1 = if ((0..1).random() == 0) Any() else null
		val arg2 = if ((0..1).random() == 0) Any() else null
		val items = arrayOfNulls<Any>((2..30).random())
		totalTrim += testTrim(arg1, arg2, items)

		/*if (it != 0 && it % 10_000 == 0)
			println("$it iteration")*/
	}

	totalExact /= 1000000.0
	totalTrim /= 1000000.0
	val pct = ((totalTrim/totalExact - 1)*100).roundToInt()
	println(  "Exact: ${totalExact.roundToInt()}" +
			"\nTrim:  ${totalTrim.roundToInt()}" +
			"\nProfit: +$pct%"
	)
}

fun testExact(arg1: Any?, arg2: Any?, items: Array<Any?>): Long {
	val start = System.nanoTime()
	var size = items.size
	val arg1 = arg1
	val arg2 = arg2
	if (arg1 != null)
		size++
	if (arg2 != null)
		size++
	val item = ArrayList<Any?>(size)
	if (arg1 != null)
		item += arg1
	if (arg2 != null)
		item += arg2
	for (i in items)
		item += i
	val end = System.nanoTime()
	return end - start
}

fun testTrim(arg1: Any?, arg2: Any?, items: Array<Any?>): Long {
	val start = System.nanoTime()
	val item = ArrayList<Any?>()
	if (arg1 != null)
		item += arg1
	if (arg2 != null)
		item += arg2
	for (i in items)
		item += i
	item.trimToSize()
	val end = System.nanoTime()
	return end - start
}