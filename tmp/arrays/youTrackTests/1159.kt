// Original bug: KT-32754

fun String.describeConstable() = 1
fun String.indent(x: Int) = ""
fun String.transform(x: (String) -> Int) = 1

fun main() {
    // All calls are resolved to the new members of java.lang.String introduced in JDK 12
    "".describeConstable()
    "".indent(1)
    "".transform {
        1
    }
}
