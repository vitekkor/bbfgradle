// Original bug: KT-22495

fun testEqualityDD(x: Double, y: Double) = x == y
fun testEqualityDA(x: Double, a: Any) = x == a
fun testEqualityDQ(x: Double, q: Any) = q is Double && x == q

fun main(args: Array<String>) {
    println(testEqualityDD(-0.0, 0.0))  // true
    println(testEqualityDA(-0.0, 0.0))  // false
    println(testEqualityDQ(-0.0, 0.0))  // false
}
