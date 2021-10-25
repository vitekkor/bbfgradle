// Original bug: KT-37707

import kotlin.reflect.jvm.isAccessible

fun main() {
    Observer::class.members.forEach { member ->
        member.isAccessible = true
    }
}

class Observer : AutoCloseable {
    override fun close() {
    }
}
