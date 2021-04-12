// Original bug: KT-45056

enum class TestA {
	AZERO, // ordinal = 0
	AONE { // ordinal = -1
		fun something() {}
	},
	ATWO // ordinal = 2
}

enum class TestB(val property: String) {
	BZERO("foo"), // ordinal = 0
	BONE("bar") { // ordinal = -1
		fun something() {}
	},
	BTWO("baz") // ordinal = 2
}

enum class TestC {
	CZERO { // ordinal = -1
		override fun something() = "something"
	},
	CONE { // ordinal = -1
		override fun something() = "everything"
	},
	CTWO { // ordinal = -1
		override fun something() = "anything"
	};

	abstract fun something(): String
}

fun main() {
	for (value in TestA.values()) {
		println("$value ${value.ordinal}")
	}
	for (value in TestB.values()) {
		println("$value ${value.ordinal}")
	}
	for (value in TestC.values()) {
		println("$value ${value.ordinal}")
	}
}
