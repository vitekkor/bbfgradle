// Original bug: KT-43962

data class Top(
    val first: Int,
    val middle: Middle,
    val third: Int
)

data class Middle(
    val first: Boolean,
    val bottom: Bottom,
    val third: Boolean
)

data class Bottom(
    val first: String,
    val second: String,
    val third: String
)
