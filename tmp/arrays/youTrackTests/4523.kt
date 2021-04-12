// Original bug: KT-32089

val fail = { throw RuntimeException("foo") } // inferred type: () -> Nothing
val s: String? = null
val test = s ?: fail() // will have new warning on this line: "One of the type variables was implicitly inferred to Nothing. Please, specify type arguments explicitly to hide this warning"
