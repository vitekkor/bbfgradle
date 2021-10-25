// Original bug: KT-23723

class PartExtended {
    fun act(value: Int) {}
}

open class PartParent<T : Any>(_y: T) {
    var y = _y
}
class PartChild(y: Int) : PartParent<Int>(y)

inline var PartExtended.propA
    get() = 0
    set(value) = act(value)

var PartExtended.propB: PartChild
    get() = PartChild(0)
    set(value) { propA = value.y }
