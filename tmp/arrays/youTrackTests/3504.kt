// Original bug: KT-37341

fun func(map: Map<String, () -> Unit>) {}

fun foo() : Unit {}

fun test() {
    func(mapOf("foo" to ::foo)) //Works
    func(mapOf("abc" to {})) //Works

    //IDE error, but compiles fine:
    func(mapOf(
        "foo" to ::foo,
        "bar" to { }
    ))

    //No error, compiles fine
    func(mapOf<String, () -> Unit>(
        "foo" to ::foo,
        "bar" to { }
    ))
}
