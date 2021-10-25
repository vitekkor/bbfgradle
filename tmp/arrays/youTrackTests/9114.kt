// Original bug: KT-16290

package kt16290

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class ClassAnnotation


@ClassAnnotation class TestClass {
}

fun main(args: Array<String>) {
    println(TestClass::class.java.annotations[0])
}
