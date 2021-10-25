// Original bug: KT-30831

const val MAX_V = 3

var tubes = Array<ArrayList<Int>>(MAX_V) { ArrayList() }

fun changeTubes() {
    tubes = Array<ArrayList<Int>>(MAX_V) { ArrayList() } // 'Remove explicit type arguments' inspection
}
