// Original bug: KT-9897

object Test {
    fun change(): Unit {
        // OK:
        arrayListOf(1).someProperty = arrayListOf(1).someProperty - 1
        
        // Bad:
        arrayListOf(1).someProperty -= 1
    }
    var MutableList<Int>.someProperty: Int
        get() {
            return this[1]
        }
        set(left) {
            this[1] = left
        }
}
