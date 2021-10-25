// Original bug: KT-35613

class GenericClass<T>

val booleanExp = true
fun intGenericFun(): GenericClass<Int> =
    if (booleanExp)
        try {
            GenericClass()
        } catch (e: Exception) {
            GenericClass()
        }
    else
        GenericClass()
