// Original bug: KT-17187

class SomeTest(var nullable: String? = null) {
	fun someMethod() {
		if (nullable != null && isCheck(nullable!!)) {
			println("nullable is not null")
		}
	}
	fun isCheck(nullable: String): Boolean {
		return nullable.isEmpty()
	}
}
