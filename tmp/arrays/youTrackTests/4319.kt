// Original bug: KT-34276

import kotlin.reflect.KProperty

class LazyDelegationBug(val iv: Int) {
    var vv: Int = iv

    operator fun getValue(bug: LazyEnumDelegationBug, property: KProperty<*>): Int {
        return vv
    }

    operator fun setValue(bug: LazyEnumDelegationBug, property: KProperty<*>, any: Any) {
        if (iv::class == any::class) vv = any as Int
    }
}

enum class LazyEnumDelegationBug {
    AA;

    // with "by spc", a java NullPointerException occurs: apparently the delegation does not require spc to be non-null before proceeding
    // without "by spc" and the equivalent get() and set(value) functions, everything works
    val spc by lazy { LazyDelegationBug(1) }
    var vv by spc
    /*var vv
        get(  ) = spc.getValue(this,spc::vv)
        set(xv) = spc.setValue(this,spc::vv,xv)
*/
}

fun main() {
    LazyEnumDelegationBug.AA.vv = 1
}
