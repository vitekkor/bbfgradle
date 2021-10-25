// Original bug: KT-14311

class C<T> { }
typealias A<T> = C<T>;

val x : C<String> = C() // OK
val y : A<String> = C() // OK
val z : A<String> = A() // Error: One type argument expected for constructor C<T>() defined in C
