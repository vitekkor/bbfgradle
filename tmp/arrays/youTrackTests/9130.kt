// Original bug: KT-16073

class A : RuntimeException() {
    override fun fillInStackTrace(): Throwable { // 'override' keyword must be prohibited, as it was in 1.0.x
        return super.fillInStackTrace()
    }
}

fun main(args: Array<String>) {
    arrayListOf("").stream() // 'stream()' call must be prohibited, because it didn't work in 1.0.x
}
