class A {
    operator fun component1() = 1
    operator fun component2() = 1
}
fun box()   {
    var (a, b) = A()
if (a == 1 && b == 1) "" else ::mp1
}