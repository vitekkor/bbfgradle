// Original bug: KT-13139

val <T> T.f: (T) -> Unit
    get() = invoker

private object invoker : (Any?) -> Unit {
    override operator fun invoke(x: Any?) {
    }
}

fun main(vararg args: String) {
    ("1".f).invoke("1") // OK
    ("1".f)("1") // OK
    "1".f("1") // Error:(12, 11) Kotlin: Type mismatch: inferred type is String but T was expected
}
