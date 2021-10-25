// Original bug: KT-22250

class Test {
    fun String.len(): Int {
        return this.length
    }
}

fun main(args: Array<String>) {
    with (Test()) {
        "foo".len()
    }
}
