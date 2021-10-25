// Original bug: KT-37634

fun interface KRunnable {
    fun invoke(): String
}

fun inA(k: KRunnable): String = k.invoke()

fun inB(k: KRunnable): String = k.invoke()

fun box(): String = inA(KRunnable { "O" }) + inB(KRunnable { "K" })

fun main() {
    println(box())
}
