// Original bug: KT-17305

import kotlin.test.assertEquals
import kotlin.test.assertNotNull

fun test(actual: Map<String, *>?) {
    assertNotNull(actual)
    assertEquals(expected = mapOf("foo" to 123), actual = actual) // [TYPE_INFERENCE_ONLY_INPUT_TYPES_WARNING] Type inference failed. The value of the type parameter T should be mentioned in input types (argument types, receiver type or expected type). Try to specify it explicitly.
}
