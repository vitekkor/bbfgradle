// Original bug: KT-41202

interface Matcher<T> {
    fun matches(actual: Object)
}

fun <T : V, U : V, V> assertThat(actual: T?, matcher: Matcher<in U?>) {
}

fun <E> arrayWithSize(size: Int): Matcher<in Array<E>?> {
    return TODO()
}

fun main() {
    val myCollection = arrayOf(1)
    assertThat(myCollection, arrayWithSize(1))
}
