// Original bug: KT-4764

package org.jetbrains.jet.plugin.completion.handlers

import java.io.File
import java.io.FileInputStream
import java.io.IOException

fun test() {
    var file = File("temp")
    var fileInputStream: FileInputStream? = null
    try {
        fileInputStream = FileInputStream(file)
    } catch (e: IOException) {
        println(fileInputStream)
    } finally {
        println(fileInputStream)
    }
}
