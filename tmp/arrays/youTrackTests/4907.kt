// Original bug: KT-30697

	sealed class X {
		class A(val a: Int): X()
		object B: X() {
			val b: Int = 10
		}
	}

	fun f(x: X) = when(x) {
		is X.A -> x.a
		// Works
		is X.B -> x.b
	}
