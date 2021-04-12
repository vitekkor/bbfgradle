// Original bug: KT-34867

annotation class Ann(val css: String = "")
class Foo {
   @Ann(css = ".div > span[]")       // temporary ?
   var prop1: Any = TODO()
   @Ann(css = "section div.content") // no injection here
   var prop2: Any = TODO()
}
