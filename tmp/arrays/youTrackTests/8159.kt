// Original bug: KT-10532

abstract class DerivedAbstract : C.Base()

open class C {
	open class Base {
		open fun m() {}
	}

	protected val _internal: String = "internal"

	companion object : DerivedAbstract() {
		override fun m() {}
	}
}
