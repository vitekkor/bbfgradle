// Original bug: KT-3869

class Test {

    fun calc(): Int {
        try {
            println("in try")

            for (i in 1..2)  {
                return 10
            }
            return 0
        }
        finally {
            println("in finally")
        }
    }
}


fun main(args: Array<String>) {
    Test().calc()
}
