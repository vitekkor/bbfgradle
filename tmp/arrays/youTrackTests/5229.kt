// Original bug: KT-33489

fun main() {
    val str = "string"
    str.apply {
        str.apply {
            this@apply // [LABEL_NAME_CLASH] There is more than one label with such a name in this scope
        }
    }
}
