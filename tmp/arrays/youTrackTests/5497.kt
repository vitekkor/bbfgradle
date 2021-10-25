// Original bug: KT-28474

class Foo {
    private var someListener: ((name: String, i: Int) -> Unit)? = null

    fun bar() {
        someListener?.invoke("aaa", 1) //expected "Add parameter to function type" quickfix
    }
}
