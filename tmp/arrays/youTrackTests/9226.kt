// Original bug: KT-7495

enum class E {
    a, b, c ;
    val text : String = "name = ${name} ordinal = ${ordinal} toString() = ${toString()}"
}

fun main(args: Array<String>) {
    E.values().forEach {
        println(it.name + ": " + it.text)
    }
}
