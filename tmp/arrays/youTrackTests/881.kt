// Original bug: KT-40689

class BoundsBuilder {
    companion object {
        private val MIN = Double.NEGATIVE_INFINITY
        private val MAX = Double.POSITIVE_INFINITY
    }
    
    var npoints = 0; private set
    var xmin = MAX; private set
    var xmax = MIN; private set
    var ymin = MAX; private set
    var ymax = MIN; private set
    
    fun isEmpty() = npoints == 0
    fun isNotEmpty() = npoints > 0

    fun reset() {
        xmin = MAX
        xmax = MIN
        ymin = MAX
        ymax = MIN
        npoints = 0
    }

    fun add(x: Double, y: Double): BoundsBuilder {
        xmin = kotlin.math.min(xmin, x)
        xmax = kotlin.math.max(xmax, x)
        ymin = kotlin.math.min(ymin, y)
        ymax = kotlin.math.max(ymax, y)
        npoints++
        //println("add($x, $y) -> ($xmin,$ymin)-($xmax,$ymax)")
        return this
    }
}
