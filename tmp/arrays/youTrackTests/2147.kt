// Original bug: KT-25337

import kotlin.reflect.full.memberProperties

fun main(args: Array<String>) {
    `has spaces`()
}

fun `has spaces`() {
    val obj = object {
        val field = listOf(object {})
    }
    println(obj.getFieldValue("field"))
}

fun Any?.getFieldValue(fieldName: String): Any? {
    return this!!.javaClass.kotlin.memberProperties.first { it.name == fieldName }.get(this)
}
