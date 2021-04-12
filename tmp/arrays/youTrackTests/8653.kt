// Original bug: KT-19814

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class TestAnnotation

class Ddd {
    @TestAnnotation
    suspend fun ddd() {}
}

open class Eee {
    @TestAnnotation
    suspend open fun eee() {}
}

fun main(vararg args: String) {
    val dddAnnotation = Ddd::class.java.declaredMethods.find { it.name == "ddd" }!!.getAnnotation(TestAnnotation::class.java)
    println ("dddAnnotation = $dddAnnotation")

    val eeeAnnotation = Eee::class.java.declaredMethods.find { it.name == "eee" }!!.getAnnotation(TestAnnotation::class.java)
    println ("eeeAnnotation = $eeeAnnotation")
}
