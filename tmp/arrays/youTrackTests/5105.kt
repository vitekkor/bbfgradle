// Original bug: KT-34465

@Target(AnnotationTarget.FIELD, AnnotationTarget.PROPERTY, AnnotationTarget.TYPE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
annotation class Parse(
    val delimiter: String
)
