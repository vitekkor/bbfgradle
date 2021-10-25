// Original bug: KT-26761

open class A
class B : A()

fun A.resolve(): Boolean {
    println("A")
    return true
}

fun B.resolve(): Boolean {
    println("B")
    return true
}

fun resolveA(a: A) {
    if (a is B) {
        a.resolve() // Resolved to B.resolve()
    }
}

fun A.smartResolve() {
    if (this is B) {
        resolve() // Resolved to B.resolve()
    }
}

fun A.verySmartResolve() =
        this is B && run {
            this.resolve() // !! Resolves to B.resolve() too !!
        }

fun main(args: Array<String>) {
    B().verySmartResolve() // Prints "B"
}
