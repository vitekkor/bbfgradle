// Original bug: KT-32649

@Deprecated(
    "Change it!",
    ReplaceWith("subject.f1(l1)\nsubject.f2(l2)\nsubject.f3(l3)")
)
fun deprecatedFunction(subject: Int, l1: (Int) -> Unit, l2: (Int) -> Unit, l3: (Int) -> Unit) {}

fun Int.f1(l: (Int) -> Unit) {}
fun Int.f2(l: (Int) -> Unit) {}
fun Int.f3(l: (Int) -> Unit) {}
