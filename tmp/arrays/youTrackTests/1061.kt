// Original bug: KT-45022

fun main(args: Array<String>) {

  //using overload function call works
  CrashingObject.plusAssign(1)
  
  CrashingObject += 1 //anything can be added, nothing works
}

object CrashingObject {
  operator fun plusAssign(any: Any) = Unit
}
