// Original bug: KT-27732

import java.util.*

@ExperimentalUnsignedTypes
fun test(sortedMap: SortedMap<UInt, String>) {

    val key: UInt = sortedMap.firstKey() //val key: UInt? = sortedMap.firstKey() also compiles successfully
}
