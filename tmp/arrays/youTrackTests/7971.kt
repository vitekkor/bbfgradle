// Original bug: KT-23991

class Vector3(var x: Double = 0.0, var y: Double = 0.0, var z: Double = 0.0) {
    init {
        println("Creating vector")
    }

    companion object {
        fun zero() = Vector3()
        fun x(x: Double = 1.0) = Vector3(x = x)
        fun y(y: Double = 1.0) = Vector3(y = y)
        fun z(z: Double = 1.0) = Vector3(z = z)
    }
}

class Basis(
    var xx: Double = 1.0, var xy: Double = 0.0, var xz: Double = 0.0,
    var yx: Double = 0.0, var yy: Double = 1.0, var yz: Double = 0.0,
    var zx: Double = 0.0, var zy: Double = 0.0, var zz: Double = 1.0
) {
    init {
        println("Creating basis")
    }

    constructor(x: Vector3 = Vector3.x(), y: Vector3 = Vector3.y(), z: Vector3 = Vector3.z()) : this(
        x.x, x.y, x.z,
        y.x, y.y, y.z,
        z.x, z.y, z.z
    )

    companion object {
        fun ident() = Basis()
        fun scale(x: Double = 1.0, y: Double = 1.0, z: Double = 1.0) = Basis(xx = x, yy = y, zz = z)
    }
}

class Transform(
    var xx: Double = 1.0, var xy: Double = 0.0, var xz: Double = 0.0, var tx: Double = 0.0,
    var yx: Double = 0.0, var yy: Double = 1.0, var yz: Double = 0.0, var ty: Double = 0.0,
    var zx: Double = 0.0, var zy: Double = 0.0, var zz: Double = 1.0, var tz: Double = 0.0
) {
    init {
        println("Creating transform")
    }

    constructor(basis: Basis = Basis.ident(), translation: Vector3 = Vector3.zero()) : this(
        basis.xx, basis.xy, basis.xz, translation.x,
        basis.yx, basis.yy, basis.yz, translation.y,
        basis.zx, basis.zy, basis.zz, translation.z
    )

    companion object {
        fun ident() = Transform()
        fun translate(x: Double = 0.0, y: Double = 0.0, z: Double = 0.0) = Transform(tx = x, ty = y, tz = z)
        fun scale(x: Double = 1.0, y: Double = 1.0, z: Double = 1.0) = Transform(xx = x, yy = y, zz = z)
    }
}

fun main(args: Array<String>) {
    Transform.ident()
}
