// Original bug: KT-18269

class Operated0(val subj: String) {
    operator fun invoke() = subj
}
class Operated1(val subj: String)
operator fun Operated1.invoke() = subj
operator fun Operated1.unaryPlus() = this.subj
fun operate(operated0: Operated0, operated1: Operated1) {
    operated0()
    operated1()
    +operated1
} 