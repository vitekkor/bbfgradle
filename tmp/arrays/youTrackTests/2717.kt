// Original bug: KT-38804

import java.io.File
import kotlin.system.exitProcess

private fun getRealFiles(): List<File> {
    //val listFileAvailable corresponding to all files in directory
    val listName = listOf("a.txt","b.jar") //and other
    return listName.map {
        val myFile = listFileAvailable.find { file -> file.name == it }
        if (myFile === null || !myFile .isFile) exitProcess(1)
        myFile
    }
}

val listFileAvailable: List<File> = TODO()
