// Original bug: KT-43275

internal class SampleTest {
    fun testString(s: String) {
        s.containsAll("First", "second")
        s.containsAll("First", "something", "other")
        s.containsAll("First", "third")
    }
}

private fun String?.containsAll(vararg strings: String) = strings.all { this?.contains(it) ?: false }
