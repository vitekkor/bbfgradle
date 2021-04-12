// Original bug: KT-10735

fun test() {
    var a: Int?  = null
    try {
        a = 3
    } catch (e: Exception) {
        return
    }
    
    a.hashCode() // a is actually never null here, but kotlin doesn't see it
}
