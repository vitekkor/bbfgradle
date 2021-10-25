// Original bug: KT-12245

enum class MyType { A, B }

@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS, AnnotationTarget.FIELD, AnnotationTarget.PROPERTY, AnnotationTarget.PROPERTY_GETTER)
annotation class MyAnnot(
		val type: MyType
)
