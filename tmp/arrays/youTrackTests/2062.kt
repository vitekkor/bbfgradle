// Original bug: KT-43064

import kotlin.reflect.KProperty

class Delegate {
    var inner = 1
    inline operator fun getValue(t: Any?, p: KProperty<*>): Int = inner
    inline operator fun setValue(t: Any?, p: KProperty<*>, i: Int) {
        inner = i
    }
}

fun box() {
    var prop by Delegate()
}
