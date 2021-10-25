// Original bug: KT-13700

interface H<T> {
    val parent : T?
}

interface A : H<A>

fun main(args: Array<String>) {
    val property = A::parent
    println(property.returnType)
}
