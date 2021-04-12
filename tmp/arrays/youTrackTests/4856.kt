// Original bug: KT-18130

fun bar(s: Any) {}
fun main(args: Array<String>) {
    var name: String?
    name = "Test"
    bar(if (true) name = null else 1) // name assigning null here
    name.hashCode() // smart cast to not-null
}
