// Original bug: KT-37183

fun profile(f: () -> Unit) {
    val s = System.currentTimeMillis()
    for (i in 0..1000000) { f() }
    val e = System.currentTimeMillis()
    println(e - s)
}

//Prints 1232 ms
public inline fun <R : Comparable<R>> DoubleArray.maxBy(selector: (Double) -> R): Double? {
    if (isEmpty()) return null
    var maxElem = this[0]
    val lastIndex = this.lastIndex
    if (lastIndex == 0) return maxElem
    var maxValue = selector(maxElem)
    for (i in 1..lastIndex) {
        val e = this[i]
        val v = selector(e)
        if (maxValue < v) {
            maxElem = e
            maxValue = v
        }
    }
    return maxElem
}


//Prints 274 ms
inline fun DoubleArray.maxBy1(): Double? {
    if (isEmpty()) return null
    var maxElem = this[0]
    var maxValue = maxElem
    for (i in 1..lastIndex) {
        val e = this[i]
        val v = e
        if (maxValue < v) {
            maxElem = e
            maxValue = v
        }
    }
    return maxElem
}

//Prints 289 ms
inline fun <R : Comparable<R>> DoubleArray.maxBy2(selector: (Double) -> R): Double? {
    if (isEmpty()) return null
    var maxElem = this[0]
    val lastIndex = this.lastIndex
    if (lastIndex == 0) return maxElem
    var maxValue = { maxElem }.invoke()
    for (i in 1..lastIndex) {
        val e = this[i]
        val v = { e }.invoke()
        if (maxValue < v) {
            maxElem = e
            maxValue = v
        }
    }
    return maxElem
}

//Prints 288 ms
inline fun DoubleArray.maxBy3(): Double? {
    if (isEmpty()) return null
    var maxElem = this[0]
    val lastIndex = this.lastIndex
    if (lastIndex == 0) return maxElem
    var maxValue = { maxElem }.invoke()
    for (i in 1..lastIndex) {
        val e = this[i]
        val v = { e }.invoke()
        if (maxValue < v) {
            maxElem = e
            maxValue = v
        }
    }
    return maxElem
}

//Prints 275 ms
inline fun DoubleArray.maxBy4(selector: (Double) -> Double): Double? {
    if (isEmpty()) return null
    var maxElem = this[0]
    val lastIndex = this.lastIndex
    if (lastIndex == 0) return maxElem
    var maxValue = selector(maxElem)
    for (i in 1..lastIndex) {
        val e = this[i]
        val v = selector(e)
        if (maxValue < v) {
            maxElem = e
            maxValue = v
        }
    }
    return maxElem
}

fun main() {
    val doubleArray = DoubleArray(1000)

    profile {
        doubleArray.maxBy { it }//Prints 1232 ms
    }
    profile {
        doubleArray.maxBy1() //Prints 274 ms
    }
    profile {
        doubleArray.maxBy2 { it } //Prints 289 ms
    }
    profile {
        doubleArray.maxBy3()//Prints 288 ms
    }
    profile {
        doubleArray.maxBy4 { it }//Prints 275 ms
    }
}
