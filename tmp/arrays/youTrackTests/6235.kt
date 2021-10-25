// Original bug: KT-30610

class Same {
    fun useInline() {
        Different().inlineFun()
        inlineFun()
    }

    inline fun inlineFun() {
        println(0) // breakpoint 0: bad
    }
}

class Different {
    inline fun inlineFun() {
        println(1) // breakpoint 1: good
    }
}

fun main() {
    Same().useInline()
}
