// Original bug: KT-17611

interface Bar {
    fun bar(param: String)
}

interface Foo {
    fun foo(param: String)
}

object X: Foo {
    override fun foo(param: String) {
        object : Bar {
            override fun bar(param: String) {
            }
        }
    }
}
