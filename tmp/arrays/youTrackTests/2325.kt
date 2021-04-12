// Original bug: KT-33590

class Wrapper() {
    init {
        println("init")
    }

    inline fun someInline(f: () -> Unit) = f()

    fun chainc(): Wrapper {
        println("omitted?")
        return Wrapper()
    }
}

fun main(args: Array<String>) {
    Wrapper()
        .chainc()
        .someInline {
            print("in lambda")
        }
}
