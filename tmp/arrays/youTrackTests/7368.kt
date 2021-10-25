// Original bug: KT-27513

inline class B(val l: Long)

inline class A(val b: B) {
    override fun toString(): String {
        return buildString {
            append("asdsa")
            append(b.l + 12)
        }
    }
}

fun main(args: Array<String>) {
    println(A(B(12)).toString())
}
