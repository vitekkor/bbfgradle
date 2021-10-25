// Original bug: KT-609

class C() {
    fun foo(s: String) {}  //should be an 'unused variable' warning
}

open class A() {
    open fun foo(s : String) {}  //should not be a warning
}

class B() : A() {
    final override fun foo(s : String) {}  //should not be a warning
}
