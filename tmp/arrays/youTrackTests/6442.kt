// Original bug: KT-17718

package refactor.inline.function3

class ExtendedClass {
    val extRefVal = 6
    var extRefVar = 7
}

fun simpleUnit(p: Int) { println(p) }
fun simpleValue(p: Int) = p
fun compoundUnit(p: Int) { println(p); println(p + 1) }
fun compoundValue(p: Int): Int { var v = p; v += 5; return v }

fun ExtendedClass.extendSimpleUnit() { println(extRefVal) }
fun ExtendedClass.extendSimpleValue() = extRefVal
fun ExtendedClass.extendCompoundUnit() { println(extRefVal); println(extRefVar) }
fun ExtendedClass.extendCompoundValue(): Int { var v = extRefVal; v += extRefVar; return v }

fun acceptUnit(p: Unit) { println(p.toString()) }
fun <T: Any> acceptValue(p: T) { println(p.hashCode()) }

fun simpleFor(): Iterable<String> = listOf("a", "b")
fun compoundFor(): Iterable<String> {
    var list = listOf("a", "b")
    list += "c"
    return list
}

fun simpleExtendedClass(flag: Boolean) = if (flag) ExtendedClass() else null
fun compoundExtendedClass(flag: Boolean): ExtendedClass? {
    val result = if (flag) ExtendedClass() else null
    return result
}

fun callUsage(isBlock: Boolean, ecn: ExtendedClass?) {
    simpleUnit(0)
    val su = simpleUnit(0)
    acceptUnit(simpleUnit(0))
    if (isBlock) simpleUnit(0)
    if (isBlock) {} else simpleUnit(0)
    if (isBlock) { println(); simpleUnit(0) }
    if (isBlock) {} else { println(); simpleUnit(0) }
    ecn?.extendSimpleUnit()

    simpleValue(1)
    val sv = simpleValue(1)
    acceptValue(simpleValue(1))
    val svb1 = if (isBlock) simpleValue(1) else 0
    val svb2 = if (isBlock) 0 else simpleValue(1)
    val svb3 = if (isBlock) { println(); simpleValue(1) } else 0
    val svb4 = if (isBlock) { simpleValue(1); 11 } else 0
    val svb5 = if (isBlock) 0 else { println(); simpleValue(1) }
    val svb6 = if (isBlock) 0 else { simpleValue(1); 11 }
    val svb7 = ecn?.extendSimpleValue()
    val svb8 = simpleExtendedClass(true) ?: simpleExtendedClass(true)

    compoundUnit(2)
    val cu = compoundUnit(2)
    acceptUnit(compoundUnit(2))
    if (isBlock) compoundUnit(2) else println()
    if (isBlock) { println(); compoundUnit(2) } else println()
    if (isBlock) { compoundUnit(2); println() } else println()
    if (isBlock) println() else compoundUnit(2)
    if (isBlock) println() else { println(); compoundUnit(2) }
    if (isBlock) println() else { compoundUnit(2); println() }
    ecn?.extendCompoundUnit()

    compoundValue(4)
    val cv = compoundValue(4)
    acceptValue(compoundValue(4))
    val cvb1 = if (isBlock) compoundValue(4) else 0
    val cvb2 = if (isBlock) { println(); compoundValue(4) } else 0
    val cvb3 = if (isBlock) { compoundValue(4); 44 } else 0
    val cvb4 = if (isBlock) 0 else compoundValue(4)
    val cvb5 = if (isBlock) 0 else { println(); compoundValue(4) }
    val cvb6 = if (isBlock) 0 else { compoundValue(4); 44 }
    val cvb7 = ecn?.extendCompoundValue()
    val cvb8 = compoundExtendedClass(true) ?: compoundExtendedClass(true)
    val cvb9 = ecn?.extendCompoundValue() ?: compoundValue(99)

    for (s in simpleFor()) simpleUnit(1)
    for (s in simpleFor()) compoundUnit(2)
    for (s in simpleFor()) { compoundUnit(3) }
    for (s in compoundFor()) compoundUnit(4)
    for (s in compoundFor()) { compoundUnit(5) }
    for (s in 6..simpleValue(7)) simpleUnit(8)
    for (s in simpleValue(9)..10) { simpleUnit(11) }
    for (s in 1..compoundValue(12)) { compoundUnit(13) }
    for (s in compoundValue(14)..15 step compoundValue(16)) compoundUnit(17)

    while (simpleValue(1) > compoundValue(2)) simpleUnit(3)
    while (simpleValue(4) > compoundValue(5)) { simpleUnit(6) }
    while (compoundValue(7) < simpleValue(8)) compoundUnit(9)
    while (compoundValue(10) < simpleValue(11)) { compoundUnit(12) }

    when (simpleValue(0)) {
        1 -> simpleUnit(1)
        2 -> { simpleUnit(2) }
        3 -> { println(); simpleUnit(3) }
        4 -> { simpleUnit(4); println() }
        else -> simpleUnit(5)
    }
    when (simpleValue(6)) {
        else -> { simpleUnit(6) }
    }
    when (compoundValue(0)) {
        1 -> compoundUnit(1)
        2 -> { compoundUnit(2) }
        3 -> { println(); compoundUnit(3) }
        4 -> { compoundUnit(4); println() }
        else -> compoundUnit(5)
    }
    when (compoundValue(6)) {
        else -> { compoundUnit(6) }
    }
    val whenVal = when (compoundValue(0)) {
        1 -> compoundValue(1)
        2 -> { compoundValue(2) }
        3 -> { println(); compoundValue(3) }
        4 -> { compoundValue(4); 44 }
        else -> compoundValue(5)
    }
    val whenValApx = when (compoundValue(6)) {
        else -> { compoundValue(6) }
    }

    val mapTo = mapOf(simpleValue(1) to compoundValue(2), compoundValue(3) to simpleValue(4))

    ecn!!.extendCompoundUnit()
    val last = ecn!!.extendCompoundValue()
}

interface DelegateFace
fun simpleDelegate() = object : DelegateFace {}
fun compoundDelegate(): DelegateFace {
    val result = object : DelegateFace {}
    return result
}
class DelegatingA : DelegateFace by simpleDelegate()
class DelegatingB : DelegateFace by compoundDelegate() 