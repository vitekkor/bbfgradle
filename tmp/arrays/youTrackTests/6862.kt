// Original bug: KT-17075

fun String.escape(): String {
    val builder = StringBuilder()
    var lastIsEscape = false
    
    this.forEach { 
        if(lastIsEscape) {
            lastIsEscape = false
            builder.append(it)
        } else if(it == '\\') {
            lastIsEscape = true  
        } else {
            builder.append(it)
        }
    }
    
    return builder.toString()
}
