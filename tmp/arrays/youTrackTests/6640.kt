// Original bug: KT-24253

package examples

import java.io.File
import java.io.IOException

@Throws(IOException::class)
fun main(args: Array<String>) {
    val tempFile = createTempFile(suffix = ".txt")
    println(tempFile)
    tempFile.deleteOnExit()

    val tempFile1 = createTempFile(suffix = ".txt", directory = File(""))
    println(tempFile1)
    tempFile1.deleteOnExit()

    val tempFile2 = createTempFile(directory = File(""))
    println(tempFile2)
    tempFile2.deleteOnExit()
}
