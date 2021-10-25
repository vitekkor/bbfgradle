// Original bug: KT-26052

inline class Username(val value: String)

fun scenarioB(name: Username?): List<Username?> = listOf(name)

fun main(args: Array<String>) {
    scenarioB(null)
}
