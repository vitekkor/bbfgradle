// Original bug: KT-36976

interface A {
    fun foo()
}
interface B {
    fun bar()
}
fun test(o: Any) {
    if (o is A && o is B) {
        o.foo() // Expected: smart cast to 'A', actual: smart cast to 'A & B'
        o.bar() // Expected: smart cast to 'B', actual: smart cast to 'A & B'
    }
}
