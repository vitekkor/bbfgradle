// Original bug: KT-45317

sealed class Media{

    abstract val url: String
}

data class PostContent(
    val text: String,
    val media: List<Media>,
)

data class PostStatistics(
    val views: ULong,
    val likes: ULong,
    val reposts: ULong,
)

data class PostMine(
    val liked: Boolean,
    val faved: Boolean,
    val hidden: Boolean,
    val reported: Boolean,
)

data class Post(
    val id: ULong,
    val title: String,
    val content: PostContent,
    val statistics: PostStatistics,
    val mine: PostMine,
)
