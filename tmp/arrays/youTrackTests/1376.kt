// Original bug: KT-22044

class Foo {
    val nonNull: String

    init {
        this.crash() // notice that it uses "nonNull" that is not initialized here
        nonNull = "Initialized"
    }
    // remember it uses "nonNull"
    fun crash() {
        nonNull.startsWith("foo") 
    }
}
