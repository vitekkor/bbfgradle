// Original bug: KT-35566

open class A<K : Number> {

	open inner class B<L> : A<Int>() where L : CharSequence {

		var x: L? = null

		inner class B<M>(m: M) : A<K>.B<M>() where M : Map<K, L> {
			init {
				x = m
			}
		}

		fun foo() = B(mapOf())
	}
}

fun main() {
	A<Number>().B<String>().foo()
}

