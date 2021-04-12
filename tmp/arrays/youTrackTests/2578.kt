// Original bug: KT-30762

interface MySeq<T> {
    fun yield(arg: T)
}

fun <T> mySeq(f: MySeq<T>.() -> Unit): MySeq<T> = null!!

class Test {
    fun repro() = mySeq<Int> {
        inner()
    }
    
    private fun inner() = mySeq<Double> {
        yield(1.0)
    }
}
