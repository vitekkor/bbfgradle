// Original bug: KT-23360

@Target(AnnotationTarget.TYPE_PARAMETER)
@Retention(AnnotationRetention.SOURCE)
annotation class A

class C {
    fun <@A T> f(x: T) {}  // <-- @A should not be serialized
}
