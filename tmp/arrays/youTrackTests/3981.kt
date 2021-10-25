// Original bug: KT-25841

interface A
interface B

class Ab1 : A, B
class Ab2 : A, B

// Error:(8, 45) Kotlin: Type inference failed. Expected type mismatch: inferred type is List<Any> but List<A> was expected
fun `as`(strs: List<String>): List<A> = strs.map {
    when(it) {
        "1" -> Ab1()
        "2" -> Ab2()
        else -> throw IllegalArgumentException()
    }
}

// Works, but...
fun `as2`(strs: List<String>): List<A> = strs.map {
    when(it) {
        "1" -> Ab1() as A // ... Idea complaints that no cast is needed. Removing the cast results in an error above
        "2" -> Ab2()
        else -> throw IllegalArgumentException()
    }
}

// Everything is fine
fun a(str: String): A = when(str) {
    "1" -> Ab1()
    "2" -> Ab2()
    else -> throw IllegalArgumentException()
}

// Also fine even though `let` has the same signature as `map`
fun a2(str: String): A = str.let {
    when(it) {
        "1" -> Ab1()
        "2" -> Ab2()
        else -> throw IllegalArgumentException()
    }
}
