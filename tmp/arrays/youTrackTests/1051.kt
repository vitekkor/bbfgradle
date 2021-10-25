// Original bug: KT-13108

enum class Example {
    Foo {
        override fun toCustomString() = this.toString() // this: Foo
    },
    Bar {
        override fun toCustomString() = this.toString() // this: Bar
    };

    // Common to all implementations
    abstract fun toCustomString(): String

    fun print() {
        // this: Example, so we can use everything that's common to both
        println(this.toCustomString())
    }
}
