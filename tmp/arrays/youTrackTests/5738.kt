// Original bug: KT-31988

enum class Foo {
    FOO() {
        override fun foo() {
            println("foo")
        }
        override var xxx: String
            get() =  "xxx"
            set(value: String) {
                println("setting xxx")
            }
    };

    abstract fun foo()
    abstract var xxx: String
}
