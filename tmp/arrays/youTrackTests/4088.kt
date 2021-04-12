// Original bug: KT-32788

sealed class Test
class TestComparable(d: List<String>): Test(), List<String> by d
fun Test.eq(that: Test): Boolean {
    return this is TestComparable
        && that is TestComparable
        && sorted() == that.sorted()
}
