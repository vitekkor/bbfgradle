// Original bug: KT-30658

import kotlin.reflect.KSuspendFunction0

fun testSerization(serialize: KSuspendFunction0<Unit>) {}

private suspend fun serialize() {}

fun test() {
    testSerization(::serialize)
}
