// Original bug: KT-14803

class A {
    val a: Int by lazy /* compilation error */ {
        val x = f()

        if (x == null) { // Quickfix: use elvis
            throw IllegalStateException("Lol")
        } // else {} does not help, but elvis does help

        x
    }
}

fun f(): Int? = null

