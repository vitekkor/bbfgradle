// Original bug: KT-31969

open class View

fun test() {
    val target = foo<View>() ?: foo() ?: run { // NI: [USELESS_ELVIS] Elvis operator (?:) always returns the left operand of non-nullable type View
        println("error")
    }
}

fun <T : View> foo(): T? {
    return null!!
}
