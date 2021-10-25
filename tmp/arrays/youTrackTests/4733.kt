// Original bug: KT-17147

private val dateTimeRegex = Regex("""^(\d+)/(\d+)/(\d+) (\d+):(\d+):(\d+)(am|pm)$""")

fun String.toDateTime(): DateTime =
    dateTimeRegex.capture(this) { (mo, day, yr, hr, min, sec, ampm) -> // Arbitrary number of captures
        DateTime(yr.toInt(), mo.toInt(), day.toInt(), hr.toInt(), min.toInt(), sec.toInt(), ampm == "pm")
    }

// Will not compile without these
private operator fun <T> List<T>.component6() = this[5]
private operator fun <T> List<T>.component7() = this[6]

data class DateTime(val year: Int, val month: Int, val day: Int, val hour: Int, val minute: Int, val second: Int, val pm: Boolean)

inline fun <R> Regex.capture(input: CharSequence, block: (captures: List<String>) -> R): R {
    val captures = matchEntire(input)?.groupValues
        ?.let { it.subList(1, it.size) }
        ?: throw Exception("Unable to match '$input` with '$this'")

    return block(captures)
}
