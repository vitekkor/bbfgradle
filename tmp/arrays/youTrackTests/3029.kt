// Original bug: KT-39231

fun main() {
    val foo = "a.*b".toRegex()   // Yes
    val bar = "a.*b".toPattern() // No

    // Workaround:
    @org.intellij.lang.annotations.Language("RegExp")
    val baz = "a.*b".toPattern() // Yes
}
