// Original bug: KT-20662

open class Base(fn: () -> Any) {
    val test = fn()
}

object Example_2_1 : Base({ Example_2_1 })

object Example_2_2 : Base(fun(): Any { return Example_2_2 })
