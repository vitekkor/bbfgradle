// Original bug: KT-17493

fun main(args: Array<String>) {
    println("Test")
}

class MyClass {
 
  fun call(x: Any){
   when(x){
     "X" -> doSomething()
   }
  }

  companion object Something : MyInterface {
   internal inline fun doSomething(){
    defaultMethod()
   }
  }
}

interface MyInterface {
 fun defaultMethod(){
  // stuff
 }
}
