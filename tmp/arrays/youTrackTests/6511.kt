// Original bug: KT-22529

class LongBuilder {
    fun longLongLong(): LongBuilder = this
    fun longLongLong(a: Any): LongBuilder = this
    fun longLongLong(a: Any, b: Any): LongBuilder = this
}

private val longBuilder: LongBuilder
    get() = LongBuilder().longLongLong().longLongLong("Some other long string", "Long string").longLongLong(12).longLongLong(111111, "Some long string").longLongLong(1122323)
