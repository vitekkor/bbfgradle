// Original bug: KT-37196

interface Base
interface Base2
interface One : Base, Base2
interface Two : Base, Base2

fun <S> intersect(vararg elements: S): S = TODO()

// OI: Base, NI: Any after approximation of {Base & Base2}
fun intersectAfterSmartCast(arg: Base, arg2: Base) = intersect(
    run {
        if (arg !is One) throw Exception()
        arg // NI: smartcast to One
    },
    run {
        if (arg2 !is Two) throw Exception()
        arg2 // NI: smartcast to Two
    }
)
