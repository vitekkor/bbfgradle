// Original bug: KT-3273

public fun testCoalesce() {
   val value: String = when {
       true -> {
          if (true) {
             "foo"
          } else {
             "bar"
          }
       }
       else -> "Hellow world"
    }
    println(value.length)
}
