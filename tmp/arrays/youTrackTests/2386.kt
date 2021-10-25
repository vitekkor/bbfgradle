// Original bug: KT-26761

open class A
class B : A()
fun A.resolve() = true
fun B.resolve() = true
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
        this.resolve() // Resolved to A.resolve(). Why?
    }
