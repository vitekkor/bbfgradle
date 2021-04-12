// Original bug: KT-6093

class NameClash {

    public val bar: String = "bar"

    public fun bar(): String {
        return "bazz"
    }
}

fun testNameClash() {
    val foo = NameClash();

    println(foo.bar)
    println(foo.bar())
    // --> Why the heck should this work?
}
