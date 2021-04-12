// Original bug: KT-30263

fun redundantConversions(long: Long, ulong: ULong) {
    val newLong = long.toLong()   // INSPECTION: Remove redundant calls of the conversion method
    val newULong = ulong.toULong() // no inspection
}
