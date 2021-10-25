// Original bug: KT-22831

fun foo() {
    var items: Array<String>? = null
    val size: Int = if (items == null) 0 else items.size // Replace if with elvis should be suggested here
}
