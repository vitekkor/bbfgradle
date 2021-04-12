// Original bug: KT-38827

abstract class Base {
    protected fun a(param: Any?) {}
    protected inline fun a() = a(null)
}
class Sub : Base() {
    init {
        a() // INVOKESTATIC Base.access$a (LBase;Ljava/lang/Object;)V
    }
}
