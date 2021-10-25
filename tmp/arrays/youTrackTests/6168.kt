// Original bug: KT-31320

class X {
    private var foo = 0
    
    private fun y() {
        x()
    }
    
    private inline fun x() {
        foo //     INVOKESTATIC kotlinx/io/X.access$getFoo$p (Lkotlinx/io/X;)I
    }
}
