// Original bug: KT-28791

inline class Float3 private constructor(val backing: FloatArray) {
    constructor(x: Float, y: Float, z: Float) : this(floatArrayOf(x, y))
    // some more fast math
}
