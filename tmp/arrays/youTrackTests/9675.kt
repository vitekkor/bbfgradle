// Original bug: KT-3515

class A {
    fun foo() {
        val ext: A.() -> Unit = { }

        ext()         // works
        this.ext()    // works
        (ext)()       // incorrect MISSING_RECEIVER: "A receiver of type A is required"
    }
}
