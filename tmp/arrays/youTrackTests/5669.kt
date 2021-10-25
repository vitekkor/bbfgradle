// Original bug: KT-18173

class HermeticClass {
	private var privateField = 1
	
	private fun privateMethod() = 1
	
	fun privateFieldHandler() = object {
		fun usePrivateField() = privateField++
	}
	
	fun privateMethodHandler() = object {
		fun usePrivateMethod() = privateMethod()
	}
}
