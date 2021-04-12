// Original bug: KT-23934

suspend fun mapString(input : String) : String {
    TODO()
}

suspend fun main() {
    val list = listOf<String>().map { mapString(it) }.joinToString()
}
