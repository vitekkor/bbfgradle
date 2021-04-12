// Original bug: KT-12734

class A {
    fun  make(param: I) {
        param.t()
    }
}

fun main(args: Array<String>) {
    val k: A? = A()
    val a = ""

    k?.let {
        k.make(object: I {
            override fun t() {
                println(a) // breakpoint
            }
        })
    }
}

interface I {
 fun t()
}
