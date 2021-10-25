// Original bug: KT-35290

sealed class CoolState
    class AwesomeState : CoolState()
    class Awesomestate : CoolState()

fun main() {
    println(Awesomestate()) // OK
    println(AwesomeState()) // java.lang.NoClassDefFoundError: b/CoolState$Awesomestate (wrong name: b/CoolState$AwesomeState)
}
