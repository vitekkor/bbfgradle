// Original bug: KT-35647

fun <T> foo(block: T.() -> Unit) {}

fun main() {
    foo<Nothing> {
        hasSurrogatePairAt(1) // no warning
    }

    foo<Nothing> {
        this.hasSurrogatePairAt(1) // 'Unreachable code'
    }
}
