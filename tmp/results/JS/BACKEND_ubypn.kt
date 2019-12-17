enum class Test : IBar {
    FOO {
class Inner : Inner by IFoo, FOO
}
}