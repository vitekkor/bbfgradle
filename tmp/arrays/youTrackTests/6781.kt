// Original bug: KT-8535


fun x(s: String?) {
    listOf("Shape" to s?.genericFunction(),"alt" to "")
    listOf<Pair<String, String?>>("Shape" to s?.genericFunction(),"alt" to "")
}

fun <T> T.genericFunction(): String = ""
