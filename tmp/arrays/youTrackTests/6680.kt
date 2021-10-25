// Original bug: KT-22737

inline fun <reified T> reified(map: T) {
    println(T::class.java.genericInterfaces.joinToString())
    println(T::class.java.genericSuperclass)
}

fun main(args: Array<String>) {
    reified(hashMapOf<String, Int>())
}
