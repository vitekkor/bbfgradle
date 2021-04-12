// Original bug: KT-24542

inline fun foo(): Boolean {
    println("FAILED")
    return false
}

fun a() = true

fun main(args: Array<String>) {
    do {
        println("1")
        if (a()) break
        println("2")
        if (a()) continue
        println("3")
    } 
    while(foo())
}
