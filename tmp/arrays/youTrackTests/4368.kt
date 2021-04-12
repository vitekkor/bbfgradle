// Original bug: KT-36767

fun foo(
    b: String? = null,
    c: Int? = null,
    d: Int? = null,
    e: Int? = null
){}

object A {
    @Deprecated(
        message = "",
        replaceWith = ReplaceWith("foo(b,c,d,e)")
    )
    fun foo(
        a: String,
        b: String? = null,
        c: Int? = null,
        d: Int? = null,
        e: Int? = null
    ) {
    }
}

fun test() {
    A.foo("Hello", null, null, null) // apply replaceWith, after applying: foo(null, null, null), expected foo("Hello")
}
