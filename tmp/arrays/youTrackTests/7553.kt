// Original bug: KT-27014

import java.io.File

fun main(args: Array<String>) {
    fun File.isAnImageFile() = listOf("jpg", "png").contains(extension)

    File("foo").isAnImageFile()
}
