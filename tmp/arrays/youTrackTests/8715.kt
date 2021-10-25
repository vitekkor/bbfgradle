// Original bug: KT-19209

public class AnnotationsOnNullableTypes {
    fun @receiver:A C?.functionWithAnnotatedReceiver() {}
}

@Target(AnnotationTarget.TYPE, AnnotationTarget.TYPE_PARAMETER, AnnotationTarget.VALUE_PARAMETER)
annotation class A

interface B<T>
interface C
