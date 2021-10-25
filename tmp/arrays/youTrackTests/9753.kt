// Original bug: KT-10845

package p

private val Empty: List<Int> = emptyList()

class C(val l: List<Int> = Empty){
    fun get(): List<Int> {
        return l.asReversed()
    }
}
