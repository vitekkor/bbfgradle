// Original bug: KT-27598

inline class InlineClass(val value: Int)
class Test(val inline: InlineClass = InlineClass(123))

fun main() {
    Test::class.constructors.first().callBy(emptyMap())
}
