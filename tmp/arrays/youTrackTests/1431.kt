// Original bug: KT-36228

package sample

inline fun copyTo(offset: Long, destinationOffset: Int = 0) {
    offset.toIntOrFail1()
}

inline fun Long.toIntOrFail1() {
    this == 5L // (1) also <= and >=
}

fun toByteArray() {}
