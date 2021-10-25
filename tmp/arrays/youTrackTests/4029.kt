// Original bug: KT-14803

class A {
    val a = f().let {
        if (it == null) throw AssertionError()
        it
    }
}

fun f(): Int? = null

fun h(x: Int) {}

fun main(args: Array<String>) {
    h(A().a) // Error Kotlin: Type mismatch: inferred type is Int? but Int was expected
}
