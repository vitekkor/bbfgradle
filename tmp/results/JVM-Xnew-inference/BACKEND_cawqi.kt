fun box() 
 {
val t:Any = TODO()
val u = false
{if (u) AssertionError() else when (t) {}}
}