// Original bug: KT-17120

    fun processEmoji(text: String): String {
        var text1 = text
        val emojiRegexps: List<Pair<Regex, String>> = listOf("cat".toRegex() to "moo", "cow".toRegex() to "meow")

        emojiRegexps.forEach { (regexp, replacement) ->
            text1 = regexp.replace(text1, replacement)
        }

        return text1.replace(Regex("fake"), " ").replace(Regex("fake"), "")
    }
