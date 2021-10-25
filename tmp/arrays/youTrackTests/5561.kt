// Original bug: KT-21490

fun main(args: Array<String>) {
  lambda()
  methodReference()
}

fun lambda() {
  val ef = ExecuteFunction { newObject().doStuff() }
  println("execute now")
  ef.execute()
}

fun methodReference() {
  val ef = ExecuteFunction(newObject()::doStuff)
  println("execute now")
  ef.execute()
}

class ExecuteFunction(val function: () -> Unit) {
  
  fun execute() {
    function()
  }
}

fun newObject() = MyObject()

class MyObject {

  init {
    println("Created")
  }

  fun doStuff() {}
}
