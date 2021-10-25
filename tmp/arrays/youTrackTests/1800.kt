// Original bug: KT-26143

open class TypeToken<T> protected constructor() {

    companion object {
        inline fun <reified T> Create(): TypeToken<T>
        {
            return object: TypeToken<T>() {}
        }
    }

    init {
        println("supertypes: ${this::class.supertypes}")
        println("genericSuperclass: ${this::class.java.genericSuperclass}")
    }
}

fun F()
{
    println("explicit code:")
    object: TypeToken<List<String>>() {}

    println("inline function:")
    TypeToken.Create<List<String>>()
}
