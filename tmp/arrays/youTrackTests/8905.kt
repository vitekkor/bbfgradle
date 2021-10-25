// Original bug: KT-10735

fun test() {
    var a: Int?  = null
    try {
        a = 3
        a = null
        a = 5
    } catch (e: Exception) {
        return
    }
    
    a.hashCode() // a is nullable here (ignoring the fact that exception is not possible at all)
}
