class S(val string: String)
class Outer
(val s: S) {
    class Nested {
        fun test(s: S) = Outer(TODO())
    }
}
fun array() = Outer.Nested().test(::AssertionError
).s.string