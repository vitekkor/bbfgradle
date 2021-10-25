// Original bug: KT-21183

interface Post {
    val userId: Int
    val id: Int
    val title: String
    val body: String
}

data class PostImpl(override val userId: Int,
                    override val id: Int,
                    override val title: String,
                    override val body: String) : Post
