// Original bug: KT-19434

open class GenericClass<T>

annotation class MethodAnnotation

inline fun <reified T> reproduceIssue() {
  val obj = object : GenericClass<T>() {  
    @MethodAnnotation fun annotatedMethod() {}
  }
  val annotatedMethod = obj::class.java.declaredMethods.first { it.name == "annotatedMethod" }
  require(annotatedMethod.annotations.isNotEmpty())
}

fun main(args: Array<String>) {
  reproduceIssue<Int>()
}
