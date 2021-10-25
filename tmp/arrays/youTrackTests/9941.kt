// Original bug: KT-4117

interface A1 {
    fun foo(): String?
}

interface A2 {
    fun foo(): String
}

class A : A1, A2 {
    override fun foo(): String {
        return ""
    }
}

fun bar(a: A1) {
    if (a is A2)
        a.foo() //overload resolution ambiguity
}
