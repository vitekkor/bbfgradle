// Original bug: KT-17526

package one
class CompanionExtraPlain { companion object { val cmpVal = 1 } }
fun wrap(p: Int) = p + 1
class Dome {
    fun refer() {
        val v1 = CompanionExtraPlain.cmpVal
        val v2 = wrap(CompanionExtraPlain.cmpVal)
    }
} 