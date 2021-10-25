// Original bug: KT-24067

class A {
    fun f(x: String, y: Int) {}
}

fun main(args: Array<String>) {
    val f = A::class.members.single { it.name == "f" }
    println(f is Function2<*, *, *>)     // false
    println(f is Function3<*, *, *, *>)  // true
    println(f::class)                    // class kotlin.reflect.jvm.internal.KFunctionImpl
}
