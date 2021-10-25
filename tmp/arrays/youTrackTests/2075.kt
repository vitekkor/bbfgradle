// Original bug: KT-40768

var log: String = "q"

class A(val a: String) {

   constructor(): this(log)
   
   companion object {
       init {
           log += "1"
       }
   }
}

fun main() {
    println(A().a)
}
