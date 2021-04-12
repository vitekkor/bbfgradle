// Original bug: KT-24739

object KtFile

class Stub {
    fun isTopLevel() = true
}

val parent = KtFile
val stub: Stub? = Stub()

fun main() {
    // prints 'false'
    println(stub?.isTopLevel() ?: parent is KtFile)

    // prints 'true' as expected
    println(stub?.isTopLevel() ?: (parent is KtFile))
}
