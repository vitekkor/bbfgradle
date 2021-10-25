// Original bug: KT-33885

object Subject1
fun alternative1(s1: Subject1) = Unit
fun alternative2(s1: Subject1) = Unit

object Subject2
fun alternative1(s2: Subject2) = Unit
fun alternative2(s2: Subject2) = Unit

fun executeActions(
    subject1Action: (Subject1) -> Unit,
    subject2Action: (Subject2) -> Unit
) {
    subject1Action(Subject1)
    subject2Action(Subject2)
}

fun f() {
    executeActions(::alternative1, ::alternative2)
}
