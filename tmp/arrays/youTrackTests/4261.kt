// Original bug: KT-16341

open class Base
class Derived : Base()

fun getBase(): Base = TODO()

class GenericTest<Any>

fun <T> genericTest(clazz: Class<T>): GenericTest<T>
        = GenericTest()

val b = genericTest(getBase()::class.java)
