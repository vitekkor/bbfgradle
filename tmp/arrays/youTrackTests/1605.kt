// Original bug: KT-11914

sealed class User {
    companion object {
        fun of(name: String, id: Int): User = UserData(if (name.isEmpty()) "Unknown" else name, id)
    }

    abstract val name: String
    abstract val id: Int

    private data class UserData(override val name: String, override val id: Int) : User()
}
