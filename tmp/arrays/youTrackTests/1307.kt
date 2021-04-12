// Original bug: KT-44440

interface I
fun test(x: I) {
    // 1.4.21: WrapperFactory<Wrapper<I>>
    // 1.4.30-RC: WrapperFactory<out Wrapper<Nothing>>
    val y = internalMultiType2(x)
}
internal fun <CX: I> internalMultiType2(
    x: CX,
    fn1: (CX) -> Unit = {},
    fn2: (CX?) -> Unit = {}
) = WrapperFactory { Wrapper(fn1, fn2) }
class WrapperFactory<W>(val creator: () -> W)
class Wrapper<in CX>(val fn1: (CX) -> Unit, val fn2: (CX?) -> Unit)
