// Original bug: KT-15777

import java.util.*
import kotlin.reflect.jvm.javaMethod

annotation class A

interface I {
    @get:A
    var p: Int
}

fun main(vararg args: String) {
    println("Via Java reflection: " + Arrays.toString(
            I::p.getter.javaMethod?.declaredAnnotations))

    println("Via Kotlin reflection: " + I::p.getter.annotations)
}
