// Original bug: KT-20978

typealias ArrayB = Array<Boolean>

fun foo(): Boolean {
    val ba = ArrayB(1) { return true }
    
    return false
}

fun bar(): Boolean {
    val ba = Array<Boolean>(1) { return true }
    
    return false
}

fun main(args: Array<String>) {
    println(foo())
    println(bar())
}
