// Original bug: KT-17733

fun <X, Y> acceptAnonymous(p: X, f: (X) -> Y) = f(p)
val viaLambda = acceptAnonymous("abc") { it.length }
val viaAnonymous = acceptAnonymous("abc", fun (p: String): Int { return p.length })
val viaAnonymousDeg = acceptAnonymous("abc", fun (String): Int { return 0 }) 