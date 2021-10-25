// Original bug: KT-20656

val val1 : Any = "String"

fun bar(){
    
}

fun foo(){
    if (val1 is String){
        val bah = if (true) ::bar else ::bar
        val str : String = val1
    }
}
