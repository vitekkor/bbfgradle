// Original bug: KT-18911

fun main(args: Array<String>) {
    // Should be KNPE!
    val f = System.getProperty("abcde")::capitalize

    // This line is successfully executed
    println(f)

    // NPE only happens here
    println(f())
}
