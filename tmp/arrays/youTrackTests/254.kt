// Original bug: KT-17966

fun main(args: Array<String>) {
    val name = run {"qwe"}        // Force compiler to actually do some concatenation
    println("${'$'}name = $name") // Wrong
    println("${"$"}name = $name") // Right
}
