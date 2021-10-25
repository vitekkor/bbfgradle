// Original bug: KT-35242

class Some1 {
    val someValue: String
            get() {
                // <caret>      TODO
                return "xxxX"
            }

    private fun a(c: Int): String {
        throw Exception("x")

        return "X".repeat(c)
    }

}
