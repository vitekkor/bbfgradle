// Original bug: KT-34932

class MyTestDate(val year: Int, val month: Int, val dayOfMonth: Int) {
    operator fun component1(): Int = year
    operator fun component2(): Int = month
    operator fun component3(): Int = dayOfMonth
}
