// Original bug: KT-29651

enum class Switch(val setting: Setting) {
    LEVER1(Setting.ON),
    POWER(Setting.OFF),
    THROTTLE(Setting.ON)
}

enum class Setting(val switches: List<Switch>) {
    ON(listOf(Switch.LEVER1)),
    OFF(listOf(Switch.LEVER1))
}

fun main(args: Array<String>) {
    println("Hmmm... Switch LEVER1: ${Switch.LEVER1.setting} Settings ON: ${Setting.ON.switches}")
}