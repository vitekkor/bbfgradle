// Original bug: KT-30920

fun main() {
    foo(A(), 42)
}

fun foo(a: A, i: Int) {
    a.operation {
        i // breakpoint
    }
}

class A
class B

fun A.operation(body: B.() -> Unit) {
    B().body()
}
