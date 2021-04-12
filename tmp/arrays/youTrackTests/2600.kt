// Original bug: KT-40997

@Deprecated(message = "...", replaceWith = ReplaceWith("..."))
public fun foo(a: Int): Unit = error("This signature is deprecated and should not be called")
