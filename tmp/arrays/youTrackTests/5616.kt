// Original bug: KT-33216

class K2J {
    fun sameSyntaxToJava(s: String) {
        val fn: (Char) -> Int = { c -> s.indexOf(c) } // Introduce for { c -> s.indexOf(c) }, check Java, undo.
    }

    fun diffSyntaxToJava(s: String) {
        val fn = { c: Char -> s.indexOf(c) } // Introduce for { c: Char -> s.indexOf(c) }, check Java, undo.
    }
}
