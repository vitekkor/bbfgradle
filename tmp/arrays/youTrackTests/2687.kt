// Original bug: KT-35249

fun main() {
    topInternalFun()
    Foo().internalFun()
    Foo().publicFun()
}
class Foo {
    internal fun internalFun() { // method breakpoint #1
        println("internalFun()")
    }
    fun publicFun(){ // method breakpoint #2
        println("publicFun()")
    }
}
internal fun topInternalFun() { // method breakpoint #3
    println("topInternalFun()")
}
