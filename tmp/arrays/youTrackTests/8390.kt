// Original bug: KT-11856

interface CV<in X> {
    fun put(e: X)
}

class Inv<X>(var e: X): CV<X> {
    override fun put(e: X) { this.e = e }
}

fun main(args: Array<String>) {
    val cv: CV<Nothing> = Inv(42)
    val inv = cv as Inv<Nothing>  // no warning
    println("Now doing the impossible: " + "${inv.e}") // compiler has proved this is unreachable
    val fail = inv.e  // ClassCastException
}
