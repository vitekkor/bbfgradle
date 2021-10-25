// Original bug: KT-26474

inline class SuperBoxString(val s: String) {
//    fun superEquals() = super.equals(Object())
//    fun superHashCode() = super.hashCode()
//    fun superToString() = super.toString()
}
fun main(args: Array<String>) {
    println(SuperBoxString(""))
}
