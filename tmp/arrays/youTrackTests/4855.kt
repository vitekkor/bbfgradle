// Original bug: KT-18130

fun main(args: Array<String>) {
    var name: String?
    name = "Test"
    "${if (true) name = null else 1}"
    name.hashCode() // Error:(5, 9) Kotlin: Only safe (?.) or non-null asserted (!!.) calls are allowed on a nullable receiver of type String?
}
