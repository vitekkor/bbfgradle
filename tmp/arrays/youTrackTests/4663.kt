// Original bug: KT-35666

sealed class Seal(val mVal: String) {

    fun doAction() {
        println("TEST $mVal")
    }

    companion object {

        object A : Seal("A")
        object B : Seal("B")

        val ALL_TYPES = listOf(A, B)

    }

}
