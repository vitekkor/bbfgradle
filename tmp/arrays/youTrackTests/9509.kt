// Original bug: KT-12863

class Testo(
    val value: Int
) {
     companion object factory {
          operator fun invoke(value: Int): Testo {
              return Testo(value + 1);
          }
     }
}

fun main(args: Array<String>) {
    println(Testo(1).value)
}
