// Original bug: KT-17799

import kotlin.reflect.KMutableProperty0

class A(var name: String)
class B(var name: String)

fun nameProperty_doesntWork(x: Any): KMutableProperty0<String> = when (x) {
    is A -> x::name // Error: Unresolved reference: name
    is B -> x::name // Error: Unresolved reference: name
    else -> throw Exception()
}

fun nameProperty_works(x: Any): KMutableProperty0<String> = when (x) {
    is A -> (x as A)::name // OK, with warning "No cast needed"
    is B -> (x as B)::name // OK, with warning "No cast needed"
    else -> throw Exception()
}
