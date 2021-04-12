// Original bug: KT-35644

fun foo(number: Int?) {
    val a = number!! // Replace '!!' with '?: return'
    println(1 + a)
}

fun main(){
    foo (null) // result is kotlin.KotlinNullPointerException
}
