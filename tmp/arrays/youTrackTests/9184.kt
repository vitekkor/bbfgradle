// Original bug: KT-15792

fun main(args: Array<String>) {
    var x: String? = ""
    val y = x
    x = null
    if (y != null) {
        x.hashCode() // Wrong smart cast, should be unsafe call (will have NPE here)
    }
}
