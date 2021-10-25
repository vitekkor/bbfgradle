// Original bug: KT-1054

fun main(args : Array<String>) {
  System.out?.println(true.and(true))
}

fun Boolean.and(other : Boolean) : Boolean{  
  if(other == true)  {  
    if(this == true){
      return true ;
     }
    else{
      return false;
    }
  }
  else {
    return false;
  }
}
