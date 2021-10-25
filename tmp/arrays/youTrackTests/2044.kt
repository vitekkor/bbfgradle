// Original bug: KT-26497

// FILE: a.kt
package foo

internal val COMMON_EMPTY = ByteString.of()
internal fun commonOf(data: ByteArray) = ByteString()

var count = 0

class ByteString {
    private val n = count++
    override fun toString() = "ByteString@$n"
    
    companion object {
        val EMPTY: ByteString = COMMON_EMPTY
        fun of(vararg data: Byte) = commonOf(data)
    }
}
