// Original bug: KT-5645

fun main(args : Array<String>) {
  var i = 0
  do {
    if (i++ > 100) break;
    continue;
  } while(false)
  println("i=${i}") // expected "i=1", actual is "i=102"
}

