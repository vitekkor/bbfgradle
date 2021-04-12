// Original bug: KT-1242

val c = object : java.util.Comparator<Int> { // Still complains
    public override fun compare(o1 : Int?, o2 : Int?) : Int {
        throw UnsupportedOperationException()
    }

}
