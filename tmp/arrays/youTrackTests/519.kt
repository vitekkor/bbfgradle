// Original bug: KT-42224

interface R
open class A : R
class B : A()

abstract class RootScope

open class AScope : RootScope() {
    @JvmName("doStuffA")
    fun doStuff(action: (A) -> Unit) = action(A())
}
fun a(configure: AScope.() -> Unit) = configure(AScope())

class BScope : AScope() {
    @JvmName("doStuffB")
    fun doStuff(action: (B) -> Unit) = action(B())
}
fun b(configure: BScope.() -> Unit) = configure(BScope())

fun main() {
    var r: R? = null

    b {
        doStuff { r = it }
    }

    r?.let {
        println("Got r = ${it::class.simpleName}")
    }
}
