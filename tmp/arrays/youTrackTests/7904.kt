// Original bug: KT-24621

var canChange: Int? = 1
fun a(t: Int) {}
fun a(t: Int?) {}

fun main(args: Array<String>) {
    if (canChange != null) {
        a(canChange) // does not compile, tries to call a(Int) but fails the smart-cast
        a(canChange as Int?) // compiles and calls a(Int?) but shows "No cast needed" hint in IntelliJ
    }
}
