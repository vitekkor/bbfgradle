// Original bug: KT-27586

fun main() {
	runInt {
		println("runInt() block begin")
		Result.success(123)
	}

	runIntAccess {
		println("runIntAccess() block begin")
		Result.success(456)
	}
}

fun runInt(block: () -> Result<Int>) {
	println("runInt() begin")
	block()
	println("runInt() end")
}

fun runIntAccess(block: () -> Result<Int>) {
	println("runIntAccess() begin")
	block().getOrNull()
	println("runIntAccess() end")
}
