// Original bug: KT-38899

fun <T : A> create(modelClass: Class<T>): T {
    return if (modelClass.isAssignableFrom(B::class.java)) { // UNREACHABLE_CODE
        createViewModel() // IMPLICIT_NOTHING_TYPE_ARGUMENT_IN_RETURN_POSITION
    } else {
        throw Exception()
    }
}

@Suppress("UNCHECKED_CAST")
fun <T : A> createViewModel(): T {
    return B() as T
}

open class A
class B : A()

fun main() {
    create(A::class.java) // NullPointerException
}
