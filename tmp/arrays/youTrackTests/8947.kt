// Original bug: KT-10848

object SomeObject {
    var aDefault = 0
    inline fun inlineWithDefaults(a: Int = aDefault, b: Int? = null) {
    }

}

fun inlinedUsage() {
    SomeObject.inlineWithDefaults(a = 1)
}
