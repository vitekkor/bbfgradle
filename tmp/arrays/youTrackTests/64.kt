// Original bug: KT-25289

open class CheckNested(a: Any) {
    class Nested

    companion object : CheckNested(Nested()) // Nested() doesn't have receiver, so there will be no error
}
