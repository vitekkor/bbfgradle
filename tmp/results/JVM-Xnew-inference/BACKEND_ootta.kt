fun Byte
.foo() = 1
fun Double.foo() = 1
fun testRef(f: () -> Int
  ):Unit = TODO()

val k =
testRef(Byte::foo  )