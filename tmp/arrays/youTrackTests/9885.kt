// Original bug: KT-3560

class JsonTo {
    operator fun invoke(value: String) {

    }
}

fun <A, B> A.foo(a: B) {}

class JsonValue {
    val String.foo: JsonTo get() = JsonTo()

    fun test() {
        "".foo("") // resolved to function, although value is more specific
    }
}
