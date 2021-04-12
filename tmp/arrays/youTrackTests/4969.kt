// Original bug: KT-34869

annotation class Ann(val css: String = "")
class Foo {
   @Ann(css = ".div > span[]")      
   var prop1: Any = TODO()

   @Ann(css = "section div.content") 
   var prop2: Any = TODO()
}
