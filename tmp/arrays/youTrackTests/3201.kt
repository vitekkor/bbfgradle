// Original bug: KT-31318

// A.kt
import kotlin.reflect.KClass

@Target(AnnotationTarget.TYPE)
annotation class MyAnn(val cls: KClass<*>)

val s: @MyAnn(Array<String>::class) String = ""

fun main() {
    val ann = ::s.returnType.annotations[0] as MyAnn
    println(ann.cls) // runtime error
}
