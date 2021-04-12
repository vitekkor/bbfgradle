// Original bug: KT-41942

class Matrix {
    var a: Int = 0

    inline fun <T> keep(callback: Matrix.() -> T): T {
        val a = this.a
        try {
            return callback()
        } finally {
            this.a = a
        }
    }
}
