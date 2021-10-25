// Original bug: KT-35510

val x1: Int = if (throw Exception()) ; // "'if' has empty body" warning only

fun foo(nothing: Nothing) {
    // "Unreachable code", "'if' has empty body" warnings but no error
    val x2 = if (nothing) ;
}
