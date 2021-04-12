// Original bug: KT-41430

fun test_1(list: List<Set<String>>) {
    list.flatMapTo(mutableSetOf()) { it } // MutableSet<String> is inferred
}

fun test_2(list: List<Set<String>>) {
    sequence<String> {
        list.flatMapTo(mutableSetOf()) { it } // MutableSet<String> is inferred
    }
}

fun test_3(list: List<Set<String>>) {
    sequence {
        list.flatMapTo(mutableSetOf()) { it } // Type is no inferred
        yield("")
    }
}

fun test_4(list: List<Set<String>>) {
    sequence {
        yield("")
        list.flatMapTo(mutableSetOf()) { it } // Type is no inferred, TYPE_MISMATCH
    }
}
