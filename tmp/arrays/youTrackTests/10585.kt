// Original bug: KT-3867

class Test {

    fun b() = if (true) throw RuntimeException() else 1

    fun calc(): Int {
        try {
            println("in try")
            return 0
        } catch (e: RuntimeException) {
            println("in catch")
            return 1
        }
        finally {
            println("in finally")
            b()
        }
    }
}


fun main(args: Array<String>) {
    Test().calc()
}
