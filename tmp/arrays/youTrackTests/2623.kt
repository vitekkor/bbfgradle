// Original bug: KT-34298



fun main() {
    run {
        val (a, b) = getPair()
        run {
            println("1: " + a)
        }
    }
    run {
        var (a, b) = getPair()  // If these variables have different names than the previous block, bug is not reproduced.
        println("2: " + a!!)
        a = null   // Interferes with value from first block.
    }   
}

private fun getPair(): Pair<Float?, Float> {
    return Pair(1f, 2f)
}

