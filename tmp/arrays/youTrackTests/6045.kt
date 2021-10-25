// Original bug: KT-31835

inline fun <reified T: Short?> testShort(value: T): T = value
inline fun <reified T: Long?> testLong(value: T): T = value

var testShort = testShort(1.toShort()) // testShort: Short implicit
var testShortWithInt = testShort(1) // testShortWithInt: Short? implicit

var testLong = testLong(1L) // testLong: Long implicit
var testLongWithInt = testLong(1) // testLongWithInt: Long? implicit
