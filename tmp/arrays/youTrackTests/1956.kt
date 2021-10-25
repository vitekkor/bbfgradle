// Original bug: KT-27655


fun main(args: Array<String>) {

  //fibonacci
  val seq = sequence {
    var f0 = 0
    var f1 = 1

    while (true) {
      val fN = f0 + f1
      yield(fN)
      f0 = f1
      f1 = fN
    }
  }

  seq

}