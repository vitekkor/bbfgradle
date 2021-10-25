// Original bug: KT-26023

interface KtInterface {

    @Deprecated("deprecated")
    val a: String

    @Deprecated("deprecated")
    fun foo()

}

class KtClass : KtInterface {
    override fun foo() {
        TODO("not implemented") 
    }

    override val a: String
        get() = TODO("not implemented") 

}
