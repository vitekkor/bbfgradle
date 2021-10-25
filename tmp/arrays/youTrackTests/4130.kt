// Original bug: KT-15922

import java.io.FileInputStream
import java.io.InputStream
import java.io.ObjectInputStream

fun readStream(stream: InputStream) {}

fun readStream(stream: ObjectInputStream) {}

fun main(args: Array<String>) {
    FileInputStream("").use(::readStream) // ERROR
}
