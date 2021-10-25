// Original bug: KT-30904

sealed class State {
    object System : State()
    object Permission : State()
    object Error : State()
    companion object {
        val stateOrder = listOfNotNull(
            State.Permission,
            State.System,
            State.Error
        )
        val unit = println("Values $stateOrder")
    }
}
fun main() {
    State.Permission
}
