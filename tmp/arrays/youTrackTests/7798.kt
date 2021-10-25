// Original bug: KT-25608

fun main(args: Array<String>) {
    val x: Any = object {
        override fun toString(): String { // "Redundant override" inspection
            return super.toString()
        }
    }
}
