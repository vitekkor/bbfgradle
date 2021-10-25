// Original bug: KT-10930

class Sample<T>(val x: T, val f: (T) -> Unit)
// Oops! Expected type mismatch: required Sample<Int?>, found Sample<Nothing?>
val sample: Sample<Int?> = Sample(null) { y -> println("$y")} 
