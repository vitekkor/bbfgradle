// Original bug: KT-5545

var sideEffect = 0

fun test(name: String?) =
        name != null &&
        when (name) {
            "foo" ->
                ++sideEffect == 0                 
            else ->
                ++sideEffect == 0
        }


fun main(args : Array<String>) {
  test(null)
  println(sideEffect)
}
