// Original bug: KT-38660

import java.util.*


val dq = ArrayDeque<Pair<Int, Int>>().apply { push(1 to 2) }


fun foo(): Int {
   return dq.pop().second // breakpoint here
}


fun main() {
   print(foo())
}
