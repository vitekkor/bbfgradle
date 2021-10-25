// Original bug: KT-31458

import kotlin.reflect.full.*

class UnsignedData(var x: UShort = 0u)

fun main() {

    val foo = UnsignedData::class.primaryConstructor?.callBy(mapOf())


}
