// Original bug: KT-32501

fun <T> expect(expected: T, actual: () -> T) {
}

var String.someProp: String?
    get() = toString()
    set(value) {}

fun main() {
    val s = ""
    expect(null) { s.someProp }
    s.someProp = "foo"
    expect("foo") { s.someProp }
}
