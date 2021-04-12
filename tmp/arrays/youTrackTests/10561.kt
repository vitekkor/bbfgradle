// Original bug: KT-4118

fun Array<String>.test() {
    class Z() {
        fun test2() {
            this@test
        }
    }
    Z().test2()
}
fun main(args: Array<String>) {
    Array<String>(2, {i -> "${i}"}).test()
}
