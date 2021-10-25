// Original bug: KT-29885

class A {
    val x = "outer"
    val y = "outer"

    companion object {
        @JvmField
        val x = "companion"

        const val y = "companion" // Field should be named "y"
    }
}
