// Original bug: KT-26558

inline class WrappedString(val value: String) {
    constructor() : this("Default")
}

inline class WrappedBoolean(val value: Boolean) {
    constructor() : this(false)
}

inline class WrappedDouble(val value: Double) {
    constructor() : this(0.0)
}

fun main(args: Array<String>) {
    // val a = WrappedString()
    // val b = WrappedBoolean()
    // val c = WrappedDouble()
}
