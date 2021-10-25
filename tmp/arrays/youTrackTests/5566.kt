// Original bug: KT-21138

interface Type<in A, out R>
fun <A, B> t(t: Type<in A, out B>) {}
//                   ^     ^  OK, got two warnings here.

typealias Alias<A, B> = Type<A, B>
fun <A, B> a(a: Alias<in A, out B>) {}
//                    ^     ^  No warnings, but it's effectively the same.
