// Original bug: KT-40261

fun main() {
    json {
        "a".to("b") // apply quick fix for the `to`
    }
}

fun json(init: Foo.() -> Unit) {}

class Foo {
    fun add(key: String, value: String) {}

    @Deprecated(message = "", replaceWith = ReplaceWith("add(this, value)"))
    fun String.to(value: String) {}
}
