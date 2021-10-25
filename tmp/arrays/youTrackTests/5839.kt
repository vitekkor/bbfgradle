// Original bug: KT-31685

import kotlin.reflect.jvm.reflect

@Target(AnnotationTarget.TYPE)
annotation class Fee(vararg val i: Int)

fun main(args: Array<String>) {
    { i: @Fee(2, 3) Int -> }.reflect()!!.parameters.first().type.annotations == { i: @Fee(2, 3) String -> }.reflect()!!.parameters.first().type.annotations
}
