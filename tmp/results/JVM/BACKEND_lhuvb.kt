interface One {
fun foo()
suspend fun foo()
class Test1 : Two by b, One by a
}