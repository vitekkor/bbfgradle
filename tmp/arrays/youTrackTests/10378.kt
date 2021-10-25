// Original bug: KT-3587

open class Variable {
    val lightVar : LightVariable = if (this is LightVariable) this else LightVariable() // missed cast here
}

class LightVariable() : Variable()

fun main(args: Array<String>) {
    Variable()
}
