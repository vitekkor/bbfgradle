// Different behavior happens on:JVM ,JS -Xuse-fir


class box {
                open fun box(x: String, zzz: Int = 1) : String = "Fail #1"
            }

public fun <T> Array<T>.block(predicate: (T) -> Boolean): Int {
    for (index in (123.toByte() downTo 17.toByte()).reversed()) {
        if (predicate(this[17])){
println("THEN");

            return index
}
    }
    return +42
}