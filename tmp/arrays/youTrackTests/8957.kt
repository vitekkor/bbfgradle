// Original bug: KT-15997

import kotlin.properties.Delegates
import kotlin.properties.ReadWriteProperty

inline fun <reified T : Any> crashMe(): ReadWriteProperty<Any?, Unit> {
  return Delegates.observable(Unit, { a, b, c -> T::class.java })
}

class CrashMe {
  var value by crashMe<Any>()
}

fun main(args: Array<String>) {
  CrashMe().value = Unit
}
