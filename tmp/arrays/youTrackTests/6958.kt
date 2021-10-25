// Original bug: KT-22138


open class LateinitWithGeneric<T : Any> {
    lateinit var prop1: T
}

class LateinitWithLongInGeneric : LateinitWithGeneric<Long>() {
    // Success: Effectively inherited from LateinitWithGeneric<Long> with Long being a class
    // because of the "Any" upper bound (?)
    
    // lateinit var prop1: kotlin.Long
}

fun caller() {
    // Success: Long literals work fine
    LateinitWithLongInGeneric().prop1 = 1L
}
