// Original bug: KT-29264

fun main() {
    Int.javaClass // [JAVA_CLASS_ON_COMPANION] The resulting type of this 'javaClass' call is Class<Int.Companion> and not Class<Int>. Please use the more clear '::class.java' syntax to avoid confusion
}
