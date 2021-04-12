// Original bug: KT-7448

interface  Interval {
    val property: String
}

interface SplittableInterval: Interval

interface IntervalWithHandler: Interval

class A<T> where T : SplittableInterval, T : IntervalWithHandler  {
    fun test(p: T) {
        p.property //workaround (p as Interval).property
    }
}
