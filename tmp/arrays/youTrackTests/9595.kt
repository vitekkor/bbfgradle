// Original bug: KT-12128

import java.util.*

object Symbol {
  @JvmStatic fun main(args:Array<String>) {
     println("Locale.getDefault(): " + Locale.getDefault())
     println("Locale.getDefault(Locale.Category.DISPLAY): " + Locale.getDefault(Locale.Category.DISPLAY))
     println("Locale.getDefault(Locale.Category.FORMAT): " + Locale.getDefault(Locale.Category.FORMAT))
     println("Properties:")
     for ((k, v) in System.getProperties()) {
        println("$k: $v")
     }
  }
}
