// Original bug: KT-16341

class GenericTest<Any>

fun <T> genericTest(clazz: Class<T>): GenericTest<T>
	= GenericTest()

val a = genericTest(0.javaClass)    // a is of type "GenericTest<Int>"
val b = genericTest(0::class.java)  // b is of unexpected type "GenericTest<out Int>"
