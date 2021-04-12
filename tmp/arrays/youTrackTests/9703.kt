// Original bug: KT-9743

fun operation(int:Int) = when (int) {
    0-> {
       val f = fun Int.() :Boolean { return true}
       f
    }
    1-> {
      val f = fun Int.() :Boolean { return true}
      f
   }
    else -> throw Exception("gah")
}
