// Original bug: KT-19294

import java.util.function.Predicate

class Test {
  fun test(s: String, predicate: Predicate<String>?) {
    val filter: ((String) -> Boolean)? =
        if (s.startsWith("*")) {
          val suffix = s.substring(1)
          if (predicate != null) {
            { v: String -> predicate.test(v) && v.startsWith(suffix) }
          }
          else {
            { v: String -> v.startsWith(suffix) }
          }
        }
        else if (predicate != null) {
          { v: String -> predicate.test(v) }  // Bogus "Type mismatch" error
        }
        else {
          null
        }
  }
}
