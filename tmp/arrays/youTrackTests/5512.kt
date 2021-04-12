// Original bug: KT-26693

import java.io.File

fun main(args: Array<String>) {
    val writer = File("hello-world.txt").bufferedReader()
    try {
        // do stuff with writer
    }
    finally {
        writer.close()
    }
}
