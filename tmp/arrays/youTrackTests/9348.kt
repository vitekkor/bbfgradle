// Original bug: KT-14429

fun main(args: Array<String>) {

    listOf("").withIndex().map {
        (x, y) -> // UNUSED_PARAMETER must be reported on y
        x.hashCode()
    }
}
