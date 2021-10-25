// Original bug: KT-15988

class Parallel {
    fun vary(left: Any?, p: Int) {}
    fun vary(left: Any?, p: String?) {}
}

fun use(pk: Parallel) {
    pk.vary(null!!, null) // see this line
} 