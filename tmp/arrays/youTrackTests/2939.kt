// Original bug: KT-29471

package abi

import java.util.function.Function

inline fun appender(append: String): Function<String, String> {
  return object : Function<String, String> {
    override fun apply(arg: String) = arg + append
  }
}
