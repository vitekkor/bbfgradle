// Original bug: KT-28489

fun case_1(b: Boolean?) {
    l1@ while (true) {
        l2@ while (true || b!!) {
            break@l1
        }
    }

    b.not() // smart cast to Boolean, NPE
}

fun main() {
    case_1(null)
}
