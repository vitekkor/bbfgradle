// Original bug: KT-18177

inline fun <T> T.isJSNumber() = (this is Int) && (this is Double) //always false in JVM, but true for JS numbers
inline fun <T> ifJSNumber(t: T, f: (Double) -> Unit) {
	if (t.isJSNumber() && t is Double) {
        f(t)
    }
}

fun main() {
    
    val n = 1
    
    println(n.toInt().isJSNumber())    // true
    println(n.toByte().isJSNumber())   // true
    println(n.toDouble().isJSNumber()) // true
    println(n.toUInt().isJSNumber())   // false
    println(n.toLong().isJSNumber())   // false
    
    ifJSNumber(n) { 
        println(it + 1.0)
        println("\"it\" is n \"smartcasted\" to Double")        
    }
    
}
