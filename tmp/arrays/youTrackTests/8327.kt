// Original bug: KT-22002

import kotlin.reflect.KClass
import kotlin.reflect.jvm.javaType

fun main(args: Array<String>) {
    println("not nullable : " + object : Type<String> {}.name)
    //    not nullable : String
    println("nullable : " + object : Type<String?> {}.name)
    //    nullable : String?
    println("inline not nullable : " + Type.of<String>().name)
    //    inline not nullable : String
    println("inline nullable : " + Type.of<String?>().name)
    //    inline nullable : String
}

interface Type<T> {
    companion object {
        inline fun <reified T> of() = object : Type<T> {}
    }

    val nullable: Boolean
        get() = extractType().isMarkedNullable

    val type: Class<T>
        get() = Class.forName(extractType().javaType.typeName) as Class<T>

    val name: String get() = type.simpleName + if (nullable) "?" else ""

    private fun extractType() = this::class.supertypes
            .first { it.classifier.let { it is KClass<*> && it.java == Type::class.java } }
            .arguments[0].type!!
}