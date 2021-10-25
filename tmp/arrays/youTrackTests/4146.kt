// Original bug: KT-7304

interface B
interface A

fun main(args : Array<String>) {
    val x : Any = object : A, B { }
    if(x is A) {
        f(x) // OK
        if(x is B) {
            g(x) // Type parameter bound for T in fun <T : A> g(x: T): kotlin.Unit where T : B is not satisfied: inferred type kotlin.Any is not a subtype of A
        }
    }
}

fun <T> f(x : T) where T : A { }
fun <T> g(x : T) where T : A, T : B { }
