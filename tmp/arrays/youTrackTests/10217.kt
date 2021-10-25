// Original bug: KT-8244

package lab

open class AbstractStateMachine() {

  open fun react(input: Int): Int {
    println("AbstractStateMachine.react: this=$this")
    return input * 1
  }

}

class FooStateMachine(): AbstractStateMachine() {

  override fun react(input: Int): Int {
    println("FooStateMachine.react: this=$this")
    return input + 2
  }

}

class BarStateMachine(): AbstractStateMachine() {

  override fun react(input: Int): Int {
    println("BarStateMachine.react: this=$this")
    return input + 3
  }

}

fun main(args: Array<String>) {

  val sm: AbstractStateMachine = FooStateMachine()
  println("sm = $sm")

  val fn: ((AbstractStateMachine, Int) -> Int) = AbstractStateMachine::react
  println("fn = $fn")

  val result = fn(sm, 7)
  println("result = $result")

}
