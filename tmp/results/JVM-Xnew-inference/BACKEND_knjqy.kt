class S(val string: String)
class Outer
(val s: S) {
    class Nested {
        fun test(s: S) = Outer(TODO())
    }
}
fun String() = Outer.Nested().test((::foo)).s.string