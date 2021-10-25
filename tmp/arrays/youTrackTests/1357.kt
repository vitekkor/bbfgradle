// Original bug: KT-43901

// FILE: Foo.kt
enum class Foo(val text: String) {
    FOO("foo"),
    BAR("bar");
    companion object {
        val first by lazy { values()[0] }  /// <--------
    }
}
// FILE: box.kt
fun box(): String {
    Foo.FOO
    return if (Foo.first === Foo.FOO) "OK" else "fail"
}

