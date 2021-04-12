// Original bug: KT-38642

data class TestData @JvmOverloads constructor(
    val value0: String? = null,
    val value1: String? = null,
    val value2: Int = 0,
    val value3: Boolean = false
)
