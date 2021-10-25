// Original bug: KT-35475

fun main() {
	"foo".foo()
}

fun String.foo() = sequence<String>{
	when (this@foo.length) {
		5 -> println("5")
		else -> {
			require(false) { "Unexpected length: ${this@foo}" } //Redundant curly braces in string template
		}
	}
}


