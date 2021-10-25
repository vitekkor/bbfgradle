// Original bug: KT-36393

fun getString(b: Boolean) = ""

private fun test(b: Boolean) {
    val s = when {
        b && (
                b ||
                        b
                ) // <- formatter inserts trailing lambda
        -> getString(  b) // <- call "Reformat file" here
        else -> ""
    }
}
