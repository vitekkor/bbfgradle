// Original bug: KT-31547

fun main() {
    null?.run { return } // [IMPLICIT_NOTHING_AS_TYPE_PARAMETER] One of the type variables was implicitly inferred to Nothing. Please, specify type arguments explicitly to hide this warning.
}
