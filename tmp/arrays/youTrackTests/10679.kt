// Original bug: KT-2281

package foo


class A() {
    fun test(s: String) {    
        println("4")
        
    }
}

class B() {
    val a: A 
      get() {
          println("2")
          return A()
      }
  
    fun test() {
        println("1")
        a.test("${if (true) println(3) else 4}")
        println("5")
    }
}

fun main(args: Array<String>) {
    B().test()  
}
