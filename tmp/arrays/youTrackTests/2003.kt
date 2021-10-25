// Original bug: KT-13323

interface Stringifier<T> {
    fun stringify(value: T): String
}

class IntStringifier : Stringifier<Int> {
    override fun stringify(value: Int) = value.toString()
}

data class StringifiedObject(val value: String) {
    companion object {
        // a weirdo's alternative to a secondary constructor
        operator fun <T> invoke(value: T, stringifier: Stringifier<T>) =
                StringifiedObject(stringifier.stringify(value))
    }
}

fun main() {
    println(StringifiedObject(123, IntStringifier()))
}
