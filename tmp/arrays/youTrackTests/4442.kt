// Original bug: KT-36352

@Retention(AnnotationRetention.RUNTIME)
annotation class SomeAnnotation

inline class SomeInlineClass(val value: String)

class SomeDataClass(val inlineClass: SomeInlineClass)
