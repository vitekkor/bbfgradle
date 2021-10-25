// Original bug: KT-23284

inline val <reified T> T.foo: String
    get() {
        println(this is T)
        return "OK"
    }

fun main(args: Array<String>) {
    println("abc".foo)
}
