// Original bug: KT-5869

fun main(args: Array<String>) {
    val iterator = object : Iterator<Int> {
        override fun next() = 1
        override fun hasNext() = true
    }

    for (i in iterator) {
    }
}
