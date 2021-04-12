// Original bug: KT-31541

var counter = 0
fun inc() = counter++

tailrec fun test(x: Int = 0, y: Int = inc(), z: Int = inc()) {
   if (x * 2 != y || z - y != 1)
       throw IllegalArgumentException("x=$x y=$y z=$z")

   if (x < 100000)
       test(x + 1) // default value for `y` initialized after default value for `z` so exception would be thrown on next call
                           // Code without `tailrec` optimization works as expected
}

fun main() {
   test()
}
