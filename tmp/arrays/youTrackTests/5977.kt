// Original bug: KT-22383

open class Common
class Specific : Common() {
    fun rename() {}
}

fun test(p: Common) {
    if (p is Specific) {
        p.rename()
    }

    p as? Specific ?: return
    p.rename()
} 