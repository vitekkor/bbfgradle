// Original bug: KT-17210

fun concat(lhs: Any, rhs: CharSequence): CharSequence {
    if (lhs is String) {
        return lhs.plus(rhs)
    } else if (lhs is StringBuilder) {
        return lhs.append(rhs)
    } else {
        return StringBuilder().append(lhs).append(rhs)
    }
}

fun main(args: Array<String>) {
    println(concat("1", "1"))
    println(concat(StringBuilder("2"), "2"))
    println(concat(3, "3"))
}
