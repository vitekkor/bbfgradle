// Original bug: KT-31682

open class B

class A {
    fun b(): B {
        return object : B() {
            private fun foo(attributes: Array<Int>) {
                join(
                    presentations = attributes.map { a(it) }
                )
            }

            fun a(attribute: Int) {}
        }
    }

    private fun join(presentations: List<Unit>) {}
}
