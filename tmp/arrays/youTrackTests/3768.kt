// Original bug: KT-22304

import java.util.concurrent.Callable

fun test(): String  = ""

inline fun String.switchMapOnce(crossinline mapper: (String) -> String): String {
  Callable(::test)
  return { mapper(this) }()
}

