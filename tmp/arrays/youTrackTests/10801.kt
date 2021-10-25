// Original bug: KT-1543

class A() {
 fun p() = true 
}

fun A.p() = false

fun main(args : Array<String>) : Unit {
  println(A().p()) // prints true . Unexpected because class definition can be in another file.
}

