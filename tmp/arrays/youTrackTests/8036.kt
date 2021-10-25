// Original bug: KT-7630

class IncClass

operator fun IncClass?.inc(): IncClass? { return null }

fun useIncClass() {
    var ic: IncClass?
    ic = IncClass()
    ic++
    // ic is considered not null here
    ic.hashCode()
}
