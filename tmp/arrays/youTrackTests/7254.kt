// Original bug: KT-28280

fun issue(firstCondition: Boolean, secondCondition: Boolean) {
    val names = mutableListOf<String>()
    when {
        firstCondition -> names += listOf("Bob", "Tom", "Ann")
        secondCondition -> names += "Jose"
        else -> names += "Mary"
    }
}
