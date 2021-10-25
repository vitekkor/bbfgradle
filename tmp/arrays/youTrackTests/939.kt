// Original bug: KT-42472

import kotlin.properties.ReadOnlyProperty

class Problem {
    val variable: Int by delegate() // delegate returns `ReadOnlyProperty<Problem, {CharSequence & Int}>`
    fun <T : CharSequence> delegate() = null as ReadOnlyProperty<Problem, T>
}

