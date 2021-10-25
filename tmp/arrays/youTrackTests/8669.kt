// Original bug: KT-14632

open class BaseGeneric<T>(val value: T)

typealias ABaseGenericInt = BaseGeneric<Int>
typealias ABaseGenericGeneral<T> = BaseGeneric<T>

class ChildGeneralTA1<T>(value:T) : ABaseGenericGeneral<T>(value = value)
class ChildIntTA1(value: Int) : ABaseGenericInt(value = value)
class ChildGeneralTA2(value:Int) : ABaseGenericGeneral<Int>(value = value)

class ChildGeneral1<T>(value:T) : BaseGeneric<T>(value = value)
class ChildInt1(value: Int) : BaseGeneric<Int>(value = value)

fun main(args: Array<String>) {
    println(ChildGeneralTA1(1).value)
    println(ChildIntTA1(2).value)
    println(ChildGeneralTA2(3).value)
    println(ChildGeneral1(4).value)
    println(ChildInt1(5).value)
}
