// Original bug: KT-39369

@Target(AnnotationTarget.TYPE)
annotation class MyAnn(val default: String = "")

val s: @MyAnn String = ""

fun main() {
    val ann = ::s.returnType.annotations[0] as MyAnn
    println(ann.default) // runtime error
}

