// Original bug: KT-18799

package my

@Retention(AnnotationRetention.SOURCE)
@Target(AnnotationTarget.CLASS)
@Repeatable
annotation class Factory(
        val factoryClass: String,
        val something: Array<Test>
)

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.CLASS)
@Repeatable
annotation class Test
