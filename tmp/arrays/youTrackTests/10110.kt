// Original bug: KT-8643

public class MyClass
{
	fun main() {
		var str: String? = null

		if (str != null)
			callback {
				method1(str!!)
			}

		if (str != null)
			callback {
				method1(str)
			}
	}

	fun callback(callback: ()->Unit) {}

	fun method1(str: String) {}
}
