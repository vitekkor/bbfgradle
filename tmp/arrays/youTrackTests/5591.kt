// Original bug: KT-33268

fun main() {
    println("Hello")
}

private const val KIBI = 1024L
private const val MIBI = KIBI * KIBI
private const val GIBI = MIBI * KIBI

@Suppress("FunctionName")
fun Int.KiB() = this * KIBI
@Suppress("FunctionName")
fun Int.MiB() = this * MIBI
@Suppress("FunctionName")
fun Int.GiB() = this * GIBI
