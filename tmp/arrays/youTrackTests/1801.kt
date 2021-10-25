// Original bug: KT-33573

@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION,
    AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
annotation class FancyRuntime

@FancyRuntime // reported with `[RUNTIME_ANNOTATION_NOT_SUPPORTED] Reflection is not supported in JavaScript target, therefore you won't be able to read this annotation in run-time`
class SomeClass
