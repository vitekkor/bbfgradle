// Original bug: KT-37747


fun smile():Any? = null

fun main() {
   // this cast will fail for sure but is not checked by the compiler
    val smiler =  smile() 
    
    println(smiler as Any)
    
}

