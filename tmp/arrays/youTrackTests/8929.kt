// Original bug: KT-17762

@kotlin.jvm.JvmName("intRangeContains")
public operator fun ClosedRange<Int>.contains(value: Double): Boolean {
    return start <= value && value <= endInclusive
}
