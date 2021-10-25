// Original bug: KT-28597

class Example(
    @Suppress("foo") private val v1: String, private val v2: String,
    private val v3: String,
    private val v4: String, @Suppress("foo") private val v5: String,
    private val v6: String
)
