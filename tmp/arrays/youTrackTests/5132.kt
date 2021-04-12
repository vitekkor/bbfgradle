// Original bug: KT-13006

import java.util.Date
import java.text.SimpleDateFormat

fun String.toDate(format: String) = SimpleDateFormat(format).parse(this)
operator fun Date.plus(day: Int) = Date(time + day * 24 * 60 * 60 * 1000L)
operator fun Date.rangeTo(other: Date) = DateRange(this, other)

class DateRange(override val start: Date, override val endInclusive: Date) : ClosedRange<Date>, Iterable<Date> {
    override fun iterator() = object : Iterator<Date> {
        private var current: Date = start

        override fun hasNext(): Boolean = current <= endInclusive
        override fun next(): Date {
            val result = current
            current += 1
            return result
        }
    }
}

fun main(args: Array<String>) {
    val start = "May 1, 2016".toDate("MMM d, yyyy")
    val end = "May 31, 2016".toDate("MMM d, yyyy")
    (start..end).filter { it.day == 1 }.forEach { println(it) }
}

