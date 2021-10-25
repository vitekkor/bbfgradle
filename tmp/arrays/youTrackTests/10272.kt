// Original bug: KT-7407

class A(val z: Int)

inline fun call(p: A, s: () -> A): Int {
    return s().z
}

fun box() : String {
    val a = A(11)
    call(A(11), fun (): A {
        return@box "123"
    })
    return "123"
}
