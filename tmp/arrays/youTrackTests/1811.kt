// Original bug: KT-35508

val REGEX = Regex("(\\x1b\\w[\\s\\S]{1,2})([\\s\\S]+?(?=\\x1b\\w[\\s\\S]{1,2}|\$))")
fun regexTest(content: String): List<String> {
    return REGEX.findAll(content).map {
        it.groupValues[1]
    }.toList()
}
