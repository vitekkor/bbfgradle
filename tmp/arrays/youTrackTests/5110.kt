// Original bug: KT-33197

fun test(condition: Boolean) {

   val list1 = // infers to MutableList<Int>
      if (condition) mutableListOf<Int>()
      else emptyList()

   val list2 = // infers to List<Int>
      if (condition) mutableListOf()
      else emptyList<Int>()

}
