// Original bug: KT-42993

import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.jvm.isAccessible
import java.util.Date

fun main() {
    Date::class.declaredMemberProperties.find { it.name == "fastTime" }?.let {
        it.isAccessible = true
        val fastTime = it.get(Date())
        println("fastTime:${fastTime}")
    }
}
