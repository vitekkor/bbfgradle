// Original bug: KT-16637

fun serialize(vararg values:Any):String {
    val builder = StringBuilder()
    for(item in values) {
        when (item) {
            is String -> builder.append(item)
            is Int -> builder.append("%04d".format(item))
            //etc.
        }
    }
    return builder.toString()
}
