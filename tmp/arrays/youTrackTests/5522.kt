// Original bug: KT-26259

enum class Option {
    OptionA,
    OptionB,
    OptionC
}

fun first(test : String) : Boolean {
    TODO()
}
fun second(number : Int) : String {
    TODO()
}

fun transform(option : Option) : Boolean {
    return when(option) {
        Option.OptionA -> first(second(10))
        Option.OptionB -> first(second(20))
        Option.OptionC -> first(second(30))
    }
}
