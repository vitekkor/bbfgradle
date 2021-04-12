// Original bug: KT-23624

inline fun foo(crossinline f: (String) -> Unit) {
    { // A-lambda captures f
        f("123")
    }()
}

fun main() {
    foo { it.length } //after inline A-lambda would be stateless and could be transformed to singletone one
}
