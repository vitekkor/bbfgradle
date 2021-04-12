// Original bug: KT-42253

import kotlin.reflect.KProperty

inline operator fun String.getValue(t:Any?, p: KProperty<*>): String = p.name + this

object ForceOutOfOrder {
    // This call forces `C$inlineFun$1` to be generated before `C` itself.
    // If `IrSourceCompilerForInline` passes a `null` optimizer to `generateMethodNode`,
    // the use of `C.$$delegatedProperties` will not be recorded anywhere.
    fun callInline() = C.inlineFun()
}

object C {
    inline fun inlineFun() = {
        val O by "K"
        O
    }()
}

fun box(): String = ForceOutOfOrder.callInline() // NoSuchFieldError
