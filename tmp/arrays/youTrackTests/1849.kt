// Original bug: KT-42151

open class L<LL>(val ll: LL)

val <PT> List<PT>.p: L<PT>
    get() {
        class PLocal<LT>(lt: LT, val pt: PT): L<LT>(lt)
        return foo2(::PLocal)
    }

fun <T1, T2, R> foo2(bb: (T1, T2) -> R): R = TODO()
