// Original bug: KT-42064

class C {
    private operator fun set(i: Int, default: String = "1234", value: String) {
        println(default)
    }

    fun test() {
        this[1] = "OK"
    }
}

fun main(args: Array<String>) {
    C().test()
}
