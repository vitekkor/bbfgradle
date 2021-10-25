// Original bug: KT-8226

class A {
    override fun equals(o: Any?) = super.equals(o)
    override fun hashCode() = super.hashCode()
}

fun main(args: Array<String>) {
    val a = A()
    val b = A()
	println(a == a)
	println(a == b)
	println(a.hashCode())
	println(b.hashCode())
}
