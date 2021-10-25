// Original bug: KT-32456

fun newTypeInferenceMutableListProblem() {
  fun returnMutableList(): MutableList<Int>? = null
  fun returnsList(): List<Int>? = null

  var mutableList: MutableList<Int>? = null
  var list: List<Int>? = null

  // Those 3 lines always work:
  mutableListOf<Int>().addAll(returnMutableList() ?: emptyList<Int>())
  mutableListOf<Int>().addAll(returnsList() ?: emptyList())
  mutableListOf<Int>().addAll(list ?: emptyList())

  // All 3 lines let the compiler crash:
  mutableListOf<Int>().addAll(returnMutableList() ?: emptyList())
  mutableListOf<Int>().addAll(mutableList ?: emptyList())
  mutableListOf<Int>().addAll(null ?: emptyList()) // Note: Invalid inspection here: "Unreachable code" on addAll-function
}
