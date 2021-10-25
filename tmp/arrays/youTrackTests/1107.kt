// Original bug: KT-45180

inline class InlineClass(val i: Int)

fun callLambda(lambda: () -> Unit) {
    lambda()
}

inline fun inlineFun(i: InlineClass, crossinline lambda: () -> Unit) = callLambda {
    print(i)
    lambda()
}

fun funCallingInlineFun(i: InlineClass) = inlineFun(i) { }
