// Original bug: KT-10666

fun foo(x: String?): Int {
    val y = x ?: return 0
    // Here 'String' is recorded in the list of 'y' possible smart cast types, 
    // regardless of the fact that 'String' is the original type of 'y'
    return y.hashCode()
}
