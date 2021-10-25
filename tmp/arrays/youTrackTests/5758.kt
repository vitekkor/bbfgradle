// Original bug: KT-29661

fun main() {
    val `one + one` = 1 + 1
    val onePlusOne = 1 + 1
    print("Try evaluating the value of the above variables in a debugger," +
            "the first one fails with \"Cannot find local variable: name = `one + one`\", " +
            "while the second one works fine.")
}
