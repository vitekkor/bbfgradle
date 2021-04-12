// Original bug: KT-25847

fun <F, T> F.castNoWhere(variableOfTargetType : T) : T {
    return this as T
}

fun <F, T> F.castWithWhere(variableOfTargetType : T) : T where F : T {
    return this as T
}

val stringVariable = 10.castNoWhere("a")
val anyVariable = 10.castWithWhere("a")
