// Original bug: KT-36500

fun main() {
    val key = "--key--"
    val name = "--name--"

    val forPaste = """
        inline val $name: ResourceKey<String> = ResourceKey("$key")
    """.trimIndent()

    val forCopy = """
        inline val $name: ResourceKey<Hotkey>
            get() = ResourceKey("$key")
    """.trimIndent()
}
