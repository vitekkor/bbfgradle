// Original bug: KT-33080

fun example(int: Int) {
    // replace 'as?' with 'as'
    val value1 = int as? Number
    // remove 'as? Int' because cast is redundant
    val value2 = int as? Int
    // replace 'as?' with 'as', and remove elvis
    val value3 = int as? Number ?: throw ClassCastException("Detailed description of error, that will never occur")
     // remove 'as?' and elvis
    val value4 = int as? Int ?: TODO()
    // Interesting case, cause value5 type inferred as Serializable
    val value5 = int as? Number ?: ""

    var nullable = int as? Int
    nullable = null
}
