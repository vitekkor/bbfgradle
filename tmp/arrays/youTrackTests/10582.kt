// Original bug: KT-3874

class Test {
    fun calc(): Int {
        for (i in 1..2)  {
            try {
                continue
            } finally {
                break;
            }
        }
        return 0
    }
}


fun main(args: Array<String>) {
    Test().calc()
}
