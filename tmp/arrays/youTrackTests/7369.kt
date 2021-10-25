// Original bug: KT-27513

inline class A(val b: Long) {
    override fun toString(): String {
        return buildString {
            append(b)
        }
    }
}

fun main(args: Array<String>) {
    println(A(12).toString())
}
