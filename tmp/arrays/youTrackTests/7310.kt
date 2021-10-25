// Original bug: KT-27928

package my

import java.time.LocalTime
import kotlin.reflect.KProperty
import kotlin.reflect.full.memberProperties

fun main(args: Array<String>) {
    val time = LocalTime.parse("10:00:00")
    println(time.hour)

    val property = LocalTime::class.memberProperties.find { it.name == "hour" } as KProperty<*>
    println(property.getter.call(time))
}
