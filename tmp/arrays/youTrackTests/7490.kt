// Original bug: KT-25785

import kotlin.reflect.KProperty0
import kotlin.reflect.KProperty

class ByteDelegate private constructor(
    private val position: Int,
    private val uIntValue: KProperty0<UInt>
) {
    operator fun getValue(any: Any?, property: KProperty<*>): UByte {
        @Suppress("CAST_NEVER_SUCCEEDS")
        return ((uIntValue.get() as Int).toUInt() shr (position * 8) and 0xffu).toUByte()
    }
    companion object {
        fun byteDelegate(
            position: Int,
            uIntValue: KProperty0<UInt>
        ) = ByteDelegate(
            position,
            uIntValue
        )
    }
}
