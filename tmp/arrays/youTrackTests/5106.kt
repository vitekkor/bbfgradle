// Original bug: KT-34465

import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.primaryConstructor

@Target(AnnotationTarget.FIELD, AnnotationTarget.PROPERTY, AnnotationTarget.TYPE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
annotation class Parse(
    val delimiter: String
)

data class TestMsg(
    @Parse(";")
    val someArray: Array<String>
)

fun a() {
    TestMsg::class.primaryConstructor!!.parameters.forEach{
        println(it.findAnnotation<Parse>())
    }
}
