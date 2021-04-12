// Original bug: KT-18483

fun x(point: Int?, start: Int, end: Int): Boolean {
    if (point != null) {
        return start <= point && point <= end
    } else {
        return false
    }
}
