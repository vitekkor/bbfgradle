// Original bug: KT-27749

@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
annotation class Bean

class A {
    @Bean fun getFoo() = 42
}
