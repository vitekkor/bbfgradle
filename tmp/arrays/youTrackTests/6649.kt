// Original bug: KT-15992

inline fun <reified T : Any?> sample() {
    println("Is nullable: ${isNullable<T>()}")
}

inline fun <reified T : Any?> isNullable(): Boolean {
    return null is T
}

fun main(args: Array<String>) {
    sample<String?>()
    sample<String>()
}

// Is nullable: true
// Is nullable: false
