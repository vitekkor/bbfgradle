// Original bug: KT-4394

abstract class Base{
  init {
     method()
  }
  
  abstract fun method()
}

class Derivative : Base(){
  val str = "String"
  
  override fun method(){
    println(str.length)
  }
}

fun main(args : Array<String>) {
  Derivative().method()
}
