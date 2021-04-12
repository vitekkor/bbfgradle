// Original bug: KT-27303

fun F()
{
    val s: String = run {
        while (true) {
            return@run "Some string"
        }
        throw Error("not reached") // <<<< Kotlin: Unreachable code
    }
    println(s)
}
