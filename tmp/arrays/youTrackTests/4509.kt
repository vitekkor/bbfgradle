// Original bug: KT-26109

class C {
	@Suppress("DEPRECATION")
	fun f() {
		if (Math.random() < 0.5) {
			normal()
		} else {
			dep()
		}
	}

	@Deprecated("")
	private fun dep() {
	}

	private fun normal() {}
}
