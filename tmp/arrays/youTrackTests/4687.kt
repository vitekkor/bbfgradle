// Original bug: KT-30244

class Properties {
    val asString: String = "meh"
    val asLong: Long = 42
}

fun <K> select(x: K, y: K): K = x

fun test() {
    select(Properties::asString, Properties::asLong) // Error
    select({1}, {""}) //Error
}
