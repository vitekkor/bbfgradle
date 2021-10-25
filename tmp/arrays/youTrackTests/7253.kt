// Original bug: KT-27650

fun f1(): () -> () -> Unit {
   return {
       suspend fun() {
           println(1)
       }
   }
}

suspend fun main() {
    f1()() // ClassCastException
}
