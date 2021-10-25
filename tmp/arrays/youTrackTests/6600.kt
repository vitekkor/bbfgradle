// Original bug: KT-28220

package kapt

import kapt.InnerEnumImport.Options.A
import kapt.InnerEnumImport.Options.B
import kapt.InnerEnumImport.Options.C

class InnerEnumImport {
  fun name(o: Options) =
      when (o) {
        A -> "a"
        B -> "b"
        C -> "c"
      }

  enum class Options {
    A,
    B,
    C
  }
}
