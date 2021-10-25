// Original bug: KT-11014

class Test {
    private abstract class Base {
        private fun duplicate(s: String) = s + s

        protected inline fun doInline(block: () -> String): String {
            return duplicate(block())
        }
    }

    private class Extender: Base() {
        private fun doSomething() {
            doInline { "Test" }
        }
    }
}
