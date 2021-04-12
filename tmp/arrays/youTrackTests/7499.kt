// Original bug: KT-26057

package test

import test.X.x

enum class X { x }

class C {
	val control = C() // no /* : C */ -> good
	val redundantHint /* : X */ = X.x // redundant
	val usefulHint /* : X */ = x // good
	fun f() {
		val control = C() // no /* : C */ -> good
		val redundantHint /* : X */ = X.x // redundant
		val usefulHint /* : X */ = x //good
	}
}
