// Original bug: KT-17051

package org.jetbrains.bio

import java.nio.file.Path
import java.nio.file.Paths

fun main(args: Array<String>) {
    val usr = Paths.get("/usr")
    val bin = Paths.get("/bin")
    print(listOf(usr, bin).map { Path::toString }.joinToString(";"))
}
