// Original bug: KT-44982

data class Foo(val name: String): Bar()

open class Bar {
   val age: Int = 0
   
   override fun toString(): String {
      return "Bar(age=$age)"
   }    
}

fun main() {
   println(Foo("foo"))   
}

