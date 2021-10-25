// Original bug: KT-25735

fun main(args: Array<String>) {
    val string: String? = null
    if (string == null) return
    val foo = string // `string` is smart-cast to `String`, but `foo` is of type `String?`
}
