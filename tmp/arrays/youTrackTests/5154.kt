// Original bug: KT-23240

fun <X, Y, Z> thisIsItXYZ(x: X, y: Y, fn: X.(Y) -> Z) = x.fn(y)
fun use() {
    thisIsItXYZ('X', 'Y') {
        "XYZ".contains(it) && "XYZ".contains(this)
    }
}
