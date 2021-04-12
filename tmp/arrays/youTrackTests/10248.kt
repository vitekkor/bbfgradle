// Original bug: KT-7273

import java.io.ByteArrayOutputStream

fun <T>a(b: () -> T) : T {
    return b()
}
fun test() : String {
    return a() {
        try {
            ByteArrayOutputStream().use { s ->
                 return@a ""
            }
        } finally {}
    }
}
fun main(args: Array<String>) {
  test()
}
