// Original bug: KT-6145

val o = object {
    public val x: Int = 42  // <--- should be a warning
}

enum class E {
    ENTRY {
        public val x: Int = -42  // <--- should be a warning
    }
}
