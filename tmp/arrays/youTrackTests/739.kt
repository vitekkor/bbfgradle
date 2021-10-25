// Original bug: KT-44006

import kotlin.io.*
import java.io.*
import kotlin.io.path.*

@ExperimentalPathApi
fun main()
{
    println(File(".gitignore").extension)
    println(File(".gitignore").nameWithoutExtension)
    println(Path(".gitignore").extension)
    println(Path(".gitignore").nameWithoutExtension)
}
