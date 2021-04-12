// Original bug: KT-38678

import java.io.File
import java.lang.IllegalStateException

fun main() {
  val pathname = "/home/neonew/Dokumente/projects/homecloud/copy-recursively/a"

  if (!File(pathname).mkdirs()) {
    throw IllegalStateException("Couldn't create basedir")
  }

  File("$pathname/test.txt").writeText("plain text file")

  File(pathname)
    .copyRecursively(File("$pathname/x"))
}
