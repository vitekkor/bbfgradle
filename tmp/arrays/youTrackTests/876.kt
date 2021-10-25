// Original bug: KT-44761

/**
 * class with a constructor [parameter].  <-- WARNING Cannot resolve symbol 'parameter'
 */
class Class1(parameter: Int) {
    val property: Int = parameter
}

/**
 * class with a constructor [property]. This works.
 */
class Class2(val property: Int)

/**
 * function with a constructor [parameter]. This works as well.
 */
fun function1(parameter: Int) { println("$parameter") }
