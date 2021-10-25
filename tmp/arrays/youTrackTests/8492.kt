// Original bug: KT-18639

import java.io.File

fun main(args: Array<String>) {
    val file1 = File("")

    val filesMap = arrayListOf<Map<File, String>>()
    fun X.filesMap(): Map<File, String> =
            files.associateBy({ file1 }, { "" })

    X(listOf(file1)).apply {
        filesMap.add(filesMap())
    }
}

private class X(val files: Iterable<File>)
