// Original bug: KT-22269

val children = listOf("Emma", "Liam", "Noah")
val child = if (children.size > 0) { // `children.size > 0` or `children.isNotEmpty()`
    children[0]
} else {
    null
}
