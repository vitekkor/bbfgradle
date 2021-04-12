// Original bug: KT-45307

@MustBeDocumented
@Retention(AnnotationRetention.BINARY)
@Target(
    AnnotationTarget.CLASS,
    AnnotationTarget.FUNCTION,
    AnnotationTarget.TYPE,
    AnnotationTarget.TYPE_PARAMETER,
    AnnotationTarget.PROPERTY
)
annotation class TestAnnotation

fun function(param: @TestAnnotation String) {}

val property: @TestAnnotation String = ""
