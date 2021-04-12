// Original bug: KT-7359

public class SomeClass (values: Iterable<Int>) {
    public val values: List<Int> = values.toList()
    private val sum = this.values.map { it + 1 }.sum()
}
