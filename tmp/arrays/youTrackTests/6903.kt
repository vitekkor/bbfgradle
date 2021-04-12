// Original bug: KT-15456

fun test1(): String {
    Array<Int>(1) {
        return "OK1;"
    }
    
    return "fail1;"
}

fun test2(): String {
    IntArray(1) {
        return "OK2;"
    }
    
    return "fail2;"
}

fun main(args: Array<String>) {
    println(test1() + test2())
}
