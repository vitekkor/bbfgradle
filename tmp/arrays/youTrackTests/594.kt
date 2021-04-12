// Original bug: KT-41223

abstract class Base(val x: Int) {
}

open class Outer(val x: Int) {
    inner class Inner {
        fun useX() {
            object : Base(x) {

            }
        }
    }

}
