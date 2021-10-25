// Original bug: KT-10532

open class C {
	interface Interface {
		fun m()
	}

	open class Base : Interface {
		override fun m() {}
	}

	protected val _internal: String = "internal"

	companion object : Interface by Base() {
		override fun m() {}
	}
}

class D : C() {
	companion object : Interface by C.Companion {
		override fun m() {}
	}
}
