// Original bug: KT-28158

fun main() {
    val comment = "😃😃😃😃😃😃"
    val regex = Regex("(.{3,})\\1+", RegexOption.IGNORE_CASE)
    println(comment.contains(regex))
}
