// Original bug: KT-43285

abstract class AbstractFoo {
    abstract val type: Int

    companion object {
        const val TYPE_FIRST = 0
        const val TYPE_SECOND = 1
    }
}
