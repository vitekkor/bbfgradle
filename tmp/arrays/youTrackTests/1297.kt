// Original bug: KT-37878

fun <T : Any?> printClassName(value: T) {
    val actualClassName = when (value) {
        null -> "Nothing?"
        else -> (value as Any)::class.java.name  // 1
//        else -> value::class.java.name  // 2
//        else -> value!!::class.java.name  // 3
    }

    println(actualClassName)
}
