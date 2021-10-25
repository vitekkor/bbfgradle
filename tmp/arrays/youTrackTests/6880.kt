// Original bug: KT-10873

class X {
    fun bar() {} // member
}

fun test(
        x: X, 
        bar: X.() -> Unit // parameter
) {
    x.bar() // was resolved to parameter bar instead of member bar
}
