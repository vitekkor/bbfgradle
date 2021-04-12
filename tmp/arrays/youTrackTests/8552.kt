// Original bug: KT-19261

val funcReference = ::func

fun func(): Any = true

fun main(args: Array<String>) {
    val output = funcReference.invoke()
    when {
        output is Unit -> print(true)
        output::class != Boolean::class -> throw Exception()
        // Here
        else -> printBool(output as Boolean)
    }
}

fun printBool(bool: Boolean) {
    println(bool)
}
