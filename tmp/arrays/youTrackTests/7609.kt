// Original bug: KT-22649

import java.util.function.Consumer

class SubjClass(var subjVar: Int) {}

inline var SubjClass.apx:Int
    get() = 0
    set(value) { subjVar = value }

fun context() {
    val subj = SubjClass(1)
    Consumer<Int> { subj.apx = it }
}
