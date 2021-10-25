// Original bug: KT-7043

inline fun multiplyBy2(x: Int): Int = x + x

val one: Int
  get() {
    println("Get 1")
    return 1
  }


fun main(args : Array<String>) {
  println(multiplyBy2(one))
}
