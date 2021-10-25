// Original bug: KT-3293

import java.io.File

var File.blah : Long
      get() = this.lastModified()
      set(value) {
         this.setLastModified(value)
      }

fun bug1() {
   val f : File? = null
   f?.blah = 100
}
