// Original bug: KT-30419

interface A {
    fun foo(): Any
}

class B : A {
    override fun foo(): String { // Covariant override, return type is more specialized than in the parent
        return ""
    }
}
