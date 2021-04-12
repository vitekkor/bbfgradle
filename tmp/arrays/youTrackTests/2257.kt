// Original bug: KT-35165

private fun foo(arg: Any): Int {
    
    val x = 
        if (arg is String) 
            (arg.length + 1).takeIf { it > 4 }
        else
            arg.hashCode()
    
    // Inspection: If-null foldable to ?:
    if (x == null) return 0
    
    return x
}
