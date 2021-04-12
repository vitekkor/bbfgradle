// Original bug: KT-38956

class A {}
@Deprecated("a", ReplaceWith("this.bar()"))
fun A.foo() = {}
fun A.bar() = {}
fun test() {
    val a = A()
    a.foo() // replacement works as expected, results in a.bar()
    a::foo  // replacement does not work, results in a::this.bar()
}
