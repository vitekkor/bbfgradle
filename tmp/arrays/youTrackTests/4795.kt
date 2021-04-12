// Original bug: KT-35493

open class GenericClass<T>(val field: T)
open class OutGenericClass<out T>(val field: T)
class SubclassWithGenericField(field: GenericClass<String>) : GenericClass<GenericClass<String>>(field)
class SubclassWithOutGenericField(field: OutGenericClass<String>) : GenericClass<OutGenericClass<String>>(field)

fun <T> genericFun(o: GenericClass<T>): T = o.field
fun getOutGenericObject(): OutGenericClass<String> = OutGenericClass("a string")
