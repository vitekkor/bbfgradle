// Original bug: KT-33638

class Holder(val list: List<String>?)

fun test() {
  //val holder = Holder(emptyList()) // No problem
  //val holder = Holder(if(true) emptyList<String>() else null) // No problem
  //val holder = Holder(if(true) emptyList() else mutableListOf()) // No problem
  val holder = Holder(if(true) emptyList() else null) // Compile error
}
