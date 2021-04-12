// Original bug: KT-39257

typealias ActionPresenter = (String) -> String?

interface MethodRef<F>

class DumbMethodRef<F>(val f: F) : MethodRef<F>

data class Action(val presenter: MethodRef<ActionPresenter>?)

fun main() {
  Action(DumbMethodRef { _ ->
    "Hello"
  })
}
