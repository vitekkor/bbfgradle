// Original bug: KT-27665

abstract class Foo<TViewState : FooState> {
    open inner class ViewStateProxy : FooState
}

interface FooState

class Bar : Foo<FooState>() {
    fun bar() = ViewStateProxy()
}
