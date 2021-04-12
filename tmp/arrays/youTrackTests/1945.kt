// Original bug: KT-28415

/**
 * @author Sola
 */

class A(val a: String, val b: String?)

class B(val b: String)

class C(val a: String, val b: B?)

fun bar(b: String) = B(b)

fun A.bar(): C = C(
    a,
    b?.let<String, B>(::bar)
)
