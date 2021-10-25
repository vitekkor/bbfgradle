// Original bug: KT-14657

sealed class Command(val name: String) {
    abstract fun handler()
}
class Command1 : Command("print") {
    override fun handler() {}
}
class Command2 : Command("nop") {
    override fun handler() {}
}
class Command3 : Command("branch")  {
    val additionalData = "..."
    override fun handler() {}
}
