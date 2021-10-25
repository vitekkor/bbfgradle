// Original bug: KT-25784

import kotlin.reflect.KProperty
import kotlin.reflect.KProperty0

class ByteDelegate(
    private val position: Int,
    private val uIntValue: KProperty0<UInt>
) {
    operator fun getValue(any: Any?, property: KProperty<*>): UByte {
        val uInt = uIntValue.get() shr (position * 8) and 0xffu
        return uInt.toUByte()
    }
}
