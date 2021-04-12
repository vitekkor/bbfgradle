// Original bug: KT-42635

inline class Temperature(val inCelsius: Double)

fun parseTemperatures(line: String): List<Temperature?> {
    return line
            .split(" ")
            .map { if (it == "ISA") null else Temperature(it.toDouble()) }
}

fun main() {
    val list = parseTemperatures("10 20 30").map { it!! }
    for ((index, temperature) in list.withIndex()) {
        println(temperature)
    }
}
