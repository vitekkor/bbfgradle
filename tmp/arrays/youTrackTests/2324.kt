// Original bug: KT-33590

package kotlinjs.test

class Wrapper() {
    inline fun someInline(x: Int, f: (Int) -> Unit) = f(x*x)
    fun chainc(): Wrapper {
        println("omitted?")
        return Wrapper()
    }
}

fun main(args: Array<String>) {
    Wrapper()
        .chainc()
        .someInline(2) {
            print("in lambda $it")
        }
}
