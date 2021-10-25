// Original bug: KT-21146

object Test {

    @JvmStatic
    fun test() {
        action { o -> createWildcard(o) }
    }

    @JvmStatic
    private fun createWildcard(o: Any): Type<*>? {
        return null
    }

    inline fun action(crossinline f: (Any) -> Type<*>?) { }

    abstract class Type<T>

}
