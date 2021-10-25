// Original bug: KT-28370

fun main() {
    var x: String? = null
    x = ""

    try {
        x = null
    } catch (e: Exception) {
        x.length // smartcast shouldn't be allowed (OOME could happen after `x = null`)
        throw e
    }
    finally {
        // smartcast shouldn't be allowed, `x = null` could've happened
        x.length
    }
    // smartcast shouldn't be allowed, `x = null` could've happened
    x.length
}
