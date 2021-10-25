// Original bug: KT-26669

fun returnFun0(fn: () -> Unit): (() -> Unit) -> Unit = TODO()
fun <T> returnFun1(fn: () -> T): (() -> T) -> T = TODO()
fun <T> returnFun2(fn: (T) -> T): ((T) -> T) -> T = TODO()
fun Unit.returnFun3(fn: (Unit) -> Unit): ((Unit) -> Unit) -> Unit = TODO()
fun <T> T.returnFun5(fn: (T) -> Boolean): ((T) -> T) -> T = TODO()

fun context52() {
    returnFun0 {} () {} // no inspection warning
    returnFun1 { true } () { true } // no inspection warning
    returnFun2<Boolean> { true } () { true } // no inspection warning
    Unit.returnFun3 {} () {} // inspection warning
    25.returnFun5 { true } () { it * 2 } // inspection warning
}
