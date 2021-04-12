// Original bug: KT-6750

open class A
open class B : A()

fun run(p: A?) {
    if (p is B) {
        val b = p 
        test(b) // type mismatch here : found A?, required B
                  // but "no cast needed" for: val b = p as B
    }
}

fun test(b: B) {
}
