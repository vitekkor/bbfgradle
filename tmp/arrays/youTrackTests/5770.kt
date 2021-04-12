// Original bug: KT-32454

import java.lang.System.out

fun x() {
  out.print("test") // Inspection is correctly here and will get fixed to `print("test")`

  listOf("")
      .take(10)
      .forEach { out.print(it) } // // Inspection is correctly here and only this line gets fixed to `.forEach { print(it) }`
}
