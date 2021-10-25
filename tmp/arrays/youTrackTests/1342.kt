// Original bug: KT-13077

fun main() {
    println(Byte::toInt.call(42.toByte()))  // KotlinReflectionInternalError
    println(CharSequence::get.parameters)  // KotlinReflectionInternalError
}
