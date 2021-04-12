// Original bug: KT-30745

object OuterObject {
	val prop: String get() = "outer"

	abstract class ObjectInner {
		fun f() = prop + g()
		abstract fun g(): String
	}
}

fun main(vararg args: String) {
	val innerInstance = object : OuterObject.ObjectInner() {
		override fun g() = "inner"
	}
	println(innerInstance.f())
}
