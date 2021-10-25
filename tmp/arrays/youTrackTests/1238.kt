// Original bug: KT-32351

fun main(args: Array<String>) {
  val x: Int? = 0
  val y: Int? = 0

  x?.let {
      object : Runnable {
          override fun run() {
              y?.let {
                  System.out.println("Hello")
              }
          }
      }
  }
      ?.run()
}
