// Original bug: KT-20662

open class Base(val z: Any?)

interface IFoo

object Example_4_1 : Base(object : Base(Example_4_1) {})

object Example_4_2 : Base(object : IFoo by Example_4_2 {}), IFoo

object Example_4_3 : Base(object { val test = Example_4_3 })

object Example_4_4 : Base(object { val test by Example_4_4 }) {
    operator fun getValue(thisRef: Any, prop: Any) = 42
}
