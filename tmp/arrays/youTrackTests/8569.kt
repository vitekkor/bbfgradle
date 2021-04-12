// Original bug: KT-19337

class TestCounterLoop {
    fun sumSquares(n: Int): Int {
        var result = 0
        for (i in 0..n - 1) { // NB weak warning
            result += i * i
        }
        return result
    }
}
