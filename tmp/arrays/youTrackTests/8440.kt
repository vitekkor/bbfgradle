// Original bug: KT-21146

object Test {


    @JvmStatic
    fun test() {
        action { createWildcard() }
    }

    @JvmStatic
    private fun createWildcard(): Type<*>? {
        return null
    }

    inline fun action(crossinline f: () -> Type<*>?) { }

    abstract class Type<T>

}
