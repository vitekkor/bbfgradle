// Original bug: KT-24414

data class Vec2f(var x: Float, var y: Float) {
    inline var r: Float get() = x; set(value) { x = value }
    inline var g: Float get() = y; set(value) { y = value }
}
