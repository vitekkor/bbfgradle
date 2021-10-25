// Original bug: KT-10362

val Any.sound : (String) -> Unit
    get() = { etc -> println("Woof! $etc!") }

val String.sound : (Any) -> Unit
    get() = { etc -> println("Meooow! $etc!") }

fun main(args: Array<String>) {
    "Kitty".sound("Purrr") // Woof! Purrr!; should be: Meooow! Purrr!
}
