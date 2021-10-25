// Original bug: KT-13765

fun main(args: Array<String>) {
    var x: String? = null
    
    if (x == null) {
        x = if (args.size == 1) "34" else "12"
    }
    
    println(x.length) // 
}
