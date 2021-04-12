// Original bug: KT-33829

fun main() {
    var q: Int?
    q = null
    try {
        throw Exception()
    } catch (e: Exception) {
        q = 1
    } finally {
        if (q != null) {
            print("1")
        } else {
            print("2")
        }
    }
}
