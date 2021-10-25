// Original bug: KT-19845

fun main(args: Array<String>) {
    (AnnotatedClass::class.annotations.single() as A).bs.forEach {
        println(it)
    }
}

annotation class A(vararg val bs: B)
annotation class B(val i: Int)

@A(B(1), B(2), *arrayOf(B(4), B(5)), B(6))
class AnnotatedClass
