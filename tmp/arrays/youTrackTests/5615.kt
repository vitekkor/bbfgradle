// Original bug: KT-33219

@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.TYPE,
    AnnotationTarget.TYPE_PARAMETER
)
annotation class Ann

open class Super

open class Mid : Super() {
    fun foo(): @Ann () -> Unit = { 1 }
}

class Sub : Mid()
