// Original bug: KT-44432

import kotlin.math.atan2
import kotlin.math.sqrt
import kotlin.math.PI
import kotlin.test.assertEquals

fun main(args: Array<String>) {
    assertEquals(PI, atan2(0.0, -0.0))
    assertEquals(-PI, atan2(-0.0, -0.0))
    assertEquals(PI.toFloat(), atan2(0.0f, -0.0f)) // Expected <3.1415927>, actual <3.1415925>.
    assertEquals(-PI.toFloat(), atan2(-0.0f, -0.0f)) // Expected <-3.1415927>, actual <-3.1415925>.
}
