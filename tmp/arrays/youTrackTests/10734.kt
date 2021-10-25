// Original bug: KT-2028

private val emptyArray: Array<Any?> = arrayOfNulls(0)

public class ImmutableArrayListBuilder() {
    private var array = emptyArray
}

fun main(args : Array<String>) {
    ImmutableArrayListBuilder()
}
