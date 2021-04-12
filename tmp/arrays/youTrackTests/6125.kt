// Original bug: KT-25465

class Foo {
    suspend operator fun invoke() {}
}

suspend fun test() { // Redundant 'suspend' modifier
    Foo()() // gutter icon and type hint for suspend not shown
}
