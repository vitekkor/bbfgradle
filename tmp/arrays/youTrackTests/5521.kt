// Original bug: KT-26307

@Target(AnnotationTarget.EXPRESSION, AnnotationTarget.CLASS, AnnotationTarget.PROPERTY) // FIELD is missing
@Retention(AnnotationRetention.SOURCE)
annotation class AnnForJava
