// Original bug: KT-26481

class My(val x: Int)
class Your(val x: Int)

fun Your.foo(): Int {
    println(x) // Key point (no problem if commented)
    //   v Receiver unused
    fun My.bar(): Int {
        return x
    }   
    return My(0).bar() + x
}

fun main(args: Array<String>) {
    Your(1).foo()
}
