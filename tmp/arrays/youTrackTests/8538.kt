// Original bug: KT-20577

var x = listOf<Array<String>>()
var y = arrayOf("", "")
var z = x + y // plus(elements: Array<out T>) is called, but it should be plus(element: T)
// z is now List<Any>, but was expected to be List<Array<String>>
