// Original bug: KT-2336

fun main(args: Array<String>) {
    val b: Boolean? = null
    if (b != null) {
        if (!b) {} // OK
        if (b) {} // Error: Condition must be of type jet.Boolean, but is of type jet.Boolean?
        if (b!!) {} // WARN: Unnecessary non-null assertion (!!) on a non-null receiver of type jet.Boolean?
        foo(b) // OK
    }
}

fun foo(a: Boolean) {}
