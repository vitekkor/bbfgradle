// Original bug: KT-3189

fun main(args:Array<String>) {
  val ok = Good<Int>({ it })  
  println("Ok:  ${ ok.test(1) } = 1")
  
  val bad = Bad<Int>({ it })
  println("Bad: ${ bad.test(1) } = AssertionError :( ")
}
  
class Good<T>(val a:(T)->T) {
  
    fun test(  p1: T):T = p1
  
    // Does not harm
    fun invoke(p1: T):T = throw AssertionError("This function must not be called")
}

class Bad<T>(val a:(T)->T) {
  
    fun test(  p1: T):T = a(p1) // this is the only difference from Good
  
    // Everything works perfecly if the following line is commented out
    fun invoke(p1: T):T = throw AssertionError("This function must not be called")
}
