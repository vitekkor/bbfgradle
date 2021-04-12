// Original bug: KT-6653

interface Persistable<T> {
    val id: T
    val isNew: Boolean
}

class User(
    override var id: Long?
) : Persistable<Long?> {
    override val isNew: Boolean
        get() = id == null
}
