// Original bug: KT-32454

import java.lang.Math.abs

fun x() {
  val x = abs(10) // 1. Why no inspection here?

  listOf<Int>()
      .take(10)
      .filter { abs(it) < 10 } // 2. Wrong replacement: All 3 (!) lines get replaced with `kotlin.math.abs({ abs(it) < 10 })`
}
