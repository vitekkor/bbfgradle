// Original bug: KT-38499

private class C {
    private abstract class Abstract {
        abstract fun foo()
    }

    private class Impl1 : Abstract() {
        override fun foo() {}
    }
}
