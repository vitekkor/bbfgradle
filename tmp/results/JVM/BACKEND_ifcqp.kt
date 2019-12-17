open class Outer(
 fn: () -> String
) 
val Outer.ok get() = ""
fun Outer.test()  {
class Local : Outer({ ok })
}