// Original bug: KT-16013

class Foo {
    var text: String

    init {
        with(StringBuilder()) {
            append("foo")
            append("bar")
            text = toString()
        }
    }
}
