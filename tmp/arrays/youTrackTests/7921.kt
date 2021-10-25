// Original bug: KT-17519

import java.io.File
import java.io.FileInputStream
import java.io.InputStream

fun test() {
    wrap1 {
        wrap2 {
            var stream: InputStream? = null // Inspection is incorrect here
            try {
                stream = FileInputStream(File("test"))
                stream.use { } // (1) try to comment this line
            } catch (e: Exception) {
                // (2) try to change to 'return@wrap2'
                return@wrap1 RuntimeException("${stream?.available()}")
            }
        }
    }
}

inline fun <T> wrap1(f: () -> T) = f()
inline fun <T> wrap2(f: () -> T) = f()
