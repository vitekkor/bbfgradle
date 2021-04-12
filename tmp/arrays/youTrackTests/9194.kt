// Original bug: KT-15860

fun main(args: Array<String>) {
    val clazz = AnnClass::class
    val anns = clazz.annotations
    println(anns)
}

@Target(AnnotationTarget.CLASS)
annotation class Ann1

@Ann1
data class AnnClass(var id: Int)
