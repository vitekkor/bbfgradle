// Original bug: KT-39613

open class A {
    var baz = ""
}
interface B

class Foo(val b: String) : A(), B
class Bar(val b: String, val c: String) : A(), B

val foo = if (5 == 6) {
    Foo("") // Conditional branch result of type Foo is implicitly cast to Any
} else {
    Bar("", "") // Conditional branch result of type Foo is implicitly cast to Any
}.apply {
    baz = "hi" // Unresolved reference: baz
}
