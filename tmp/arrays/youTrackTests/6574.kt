// Original bug: KT-17545

open class Older {
    protected object ProtectedObject { val inProtectedObject = 0 }
    protected class ProtectedClass { val inProtectedClass = 0 }
    protected fun protectedFun() = 0
    protected var protectedVar = 0
}

class Younger : Older() {
    protected val v1 = ProtectedObject
    val v2 = ProtectedObject.inProtectedObject
    protected val v3 = ProtectedClass()
    val v4 = ProtectedClass().inProtectedClass
    val v5 = protectedFun()
    val v6 = protectedVar
} 