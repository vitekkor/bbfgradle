// Original bug: KT-37585

object SomeObject{
  val lst: List<String>? = listOf() //imagine this is something more complex

  fun useLst(toFind: String){
     val index = lst?.indexOf(toFind)
     if(index != null && index != -1){
         //safe to use index, 
     }
     //index either null or -1, either way its "not found".
  }
}

