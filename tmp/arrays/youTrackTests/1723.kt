// Original bug: KT-21778

class SomeClass {
    private val innerObject = InnerObject()
    class InnerObject {
        private inline fun bar(action: () -> Unit) {
            action()
        }

        fun foo() {
            bar { println("foo") }
        }
    }
}
