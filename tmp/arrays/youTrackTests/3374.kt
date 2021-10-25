// Original bug: KT-15971

package foo

interface I {
    fun foo(x: Int = 1): Unit
}

class G : C, I {
    override fun foo(x: Int): Unit {
        log("G.foo($x)")
    }
}

fun box(): String {
    val g1 = G()
    g1.foo(2)
    g1.foo()

    if (log != "G.foo(2);G.foo(1);") return "log = $log"

    return "OK"
}

// helpers

fun main(args: Array<String>) {
    println(box())
}


var log = ""
fun log(a: String) {
    log += a + ";"
}

interface C {
    fun foo(x: Int): Unit {
        log("C.foo($x)")
    }
}
