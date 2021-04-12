// Original bug: KT-42472

import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class Problem {
    val variable: Int by delegate()
    private inline fun <reified T : CharSequence> delegate(): ReadOnlyProperty<Problem, T> {
        return object : ReadOnlyProperty<Problem, T> {
            override fun getValue(thisRef: Problem, property: KProperty<*>): T {
                println("T::class ${T::class}")//for debug only
                return T::class.constructors.first().call()//stub code, problem not here
            }
        }
    }
}

fun main() {
    println(Problem().variable)
}
