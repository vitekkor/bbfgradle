// Original bug: KT-12899

val strings = ArrayList<String>()

inline fun String.onlyForNotNull() {
    strings.add(this)
}

fun platformString() {
    System.getProperty("unknown").onlyForNotNull()
}
