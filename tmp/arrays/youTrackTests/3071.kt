// Original bug: KT-39749

sealed class Event {
    abstract fun accept()
}

data class MoveNodeEvent(val x: Int) : Event() {
    override fun accept() {
        TODO("Not yet implemented")
    }
}
