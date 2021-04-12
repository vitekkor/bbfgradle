// Original bug: KT-30717

import java.io.File
import java.util.*
import kotlin.test.assertEquals

fun main(args: Array<String>) {
    println("Set Path: ")
    val inputScanner = Scanner(System.`in`)
    val path = inputScanner.next()
    val folder = File(path)
    val files = folder.listFiles { _, name -> name.endsWith(".csv") }
    //val integral = 0L
    var timesteps: Long? = null
    var setup: Array<Short>? = null
    // for every file:
    files[0].bufferedReader().use { reader ->
        // search first line
        var previousSplitted: List<String>
        while (true) {
            val firstAsString: String = reader.readLine() ?: return
            // setup comma-checker
            previousSplitted = firstAsString.split(',')
            if (previousSplitted.size < 3) continue
            val thisFileSetup = arrayOf(
                afterComma(previousSplitted[0]),
                afterComma(previousSplitted[1]),
                afterComma(previousSplitted[2])
            )
            if (thisFileSetup.any { it == (-1).toShort() }) continue
            // set or check setup-array
            if (setup == null) setup = thisFileSetup
            else assert(Arrays.deepEquals(setup, thisFileSetup))
            break
        }

        while (true) {
            val currentAsString: String = reader.readLine() ?: break
            val currentSplitted = currentAsString.split(',')

            val lala = parseIt(currentSplitted, 0, setup!!) - parseIt(previousSplitted, 0, setup!!)
            if (timesteps == null) timesteps = lala
            else assert(lala == timesteps)

            previousSplitted = currentSplitted
        }
    }
}

fun parseIt(splitted: List<String>, index: Int, setup: Array<Short>): Long {
    assertEquals(afterComma(splitted[index]), setup[index])
    return splitted[index].replaceFirst(".", "").toLong()
}

fun afterComma(string: String): Short {
    val index = string.indexOf('.')
    return if (index == -1) (-1).toShort()
    else (string.length - index - 1).toShort()
}