// Original bug: KT-21314

fun test(strings: List<String>): MutableList<String> {
    val strings2 = ArrayList<String>()
    strings.filterTo(strings2) { it.length > 3 }
    return strings2
}
