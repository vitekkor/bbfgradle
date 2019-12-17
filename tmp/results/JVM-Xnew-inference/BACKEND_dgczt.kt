import kotlin.test.assertNotEquals
fun nullable(): String
 = TODO()
fun box()  {
assertNotEquals( (::nullable)!!.type,TODO())
}