// Original bug: KT-41897

interface I {
    fun f(): String?
    val v: String?
}

class C : I {
    override fun f(): String?/*caret*/ {
        return ""
    }
    override val v: String?/*caret*/
        get() = ""
}
