// Original bug: KT-24503

data class StringPair(
        val a: String,
        val b: String
) {

    constructor() : this(return, return)

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
        }
    }
}

abstract class Abs(val a: String)
class Smth : Abs {
    constructor() : super(return)

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
        }
    }
}
