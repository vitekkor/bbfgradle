// Original bug: KT-17611

open class Base {
    open fun foo(name: String) {}
}

fun test(name: String) {
    class Local : Base() {
        // name shadowed, but after renaming there is another warning
        override fun foo(name: String) {
        }
    }
}
