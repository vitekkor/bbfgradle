// Original bug: KT-33012

open class KtParentAssert<SELF : KtParentAssert<SELF>>
class KtChildAssert<SELF : KtChildAssert<SELF>> : KtParentAssert<SELF>()

fun ourTest(k: KtChildAssert<*>) {
    k.ktExt() // allowed in NI, disallowed in OI
}

private fun <T : KtChildAssert<T>> KtChildAssert<T>.ktExt(): T = TODO()
