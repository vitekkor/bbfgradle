// Original bug: KT-27606

interface Base
enum class Enum1 : Base
enum class Enum2 : Base

class Inv<S>

fun takeInvTest(inv: Inv<Base>) {}

fun <T> select(x: T, y: T): T = TODO()

fun <K> transform(f: () -> K): Inv<K> = TODO()

fun test(test1: Enum1, test2: Enum2) {
    takeInvTest(transform { select(test1, test2) })
}
