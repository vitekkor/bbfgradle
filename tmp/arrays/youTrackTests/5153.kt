// Original bug: KT-30147

typealias LongNameForSomethingSimple = Int

data class Foo(
    val first: LongNameForSomethingSimple,
    val second: LongNameForSomethingSimple,
    val third: LongNameForSomethingSimple,
    val fourth: LongNameForSomethingSimple
)
