// Original bug: KT-17929

fun main(args: Array<String>) {
    var s: String?
    s = "Test"
    try {
        s = null
    } catch (ex: Exception) {}
    s.hashCode() // smart cast to not-null
}
