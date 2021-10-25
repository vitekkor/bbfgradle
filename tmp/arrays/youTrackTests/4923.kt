// Original bug: KT-35212

class Foo {
    private val TOP_LEVEL_CONSTANT = "constant" // Private property name 'TOP_LEVEL_CONSTANT' should not contain underscores in the middle or the end

    fun foo() {
        println(TOP_LEVEL_CONSTANT)
    }
}
