// Original bug: KT-5699

fun main(args: Array<String>) {
    A().module2("1")
}

class A {

    inline public fun module2(description: String? = null): String {
        val module = ""
        return module
    }
}
