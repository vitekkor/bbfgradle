// Original bug: KT-30724

interface Base

fun <K> materialize(): K = TODO()

fun <T : Base> Base.transform(): T = materialize()

fun test(child: Base) {
    child == child // OK
    child == child.transform() // [EQUALS_MISSING] No method 'equals(Any?): Boolean' available
}
