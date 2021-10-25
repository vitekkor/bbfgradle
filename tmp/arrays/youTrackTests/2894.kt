// Original bug: KT-38844

fun main() {
    println(TestClass::class.java.getField("test").getAnnotation(Ann::class.java)) // null
}

class TestClass {
    @JvmField
    @Ann("str")
    var test = ""
}

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD, AnnotationTarget.PROPERTY)
annotation class Ann(val value: String)
