// Original bug: KT-24777

inline fun foo() = false

fun run(x: Boolean) {
    do {     
        do { } while (false)         
        if (x) continue
    } while(foo())
}

fun main(args: Array<String>) {
    run(true)
}
