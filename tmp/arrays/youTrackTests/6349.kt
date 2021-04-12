// Original bug: KT-20801

var b: Boolean = true

fun namedFunction() = if (b) 42 as Int? else null
val functionLiteral = if (b) 42 as Int? else null
val property = if (b) 42 as Int? else null
val propertyWithAccessor: Int?
    get() = if (b) 42 as Int? else null
