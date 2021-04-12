// Original bug: KT-20305

sealed class Msg
object IncrementCounter : Msg()
object DecrementCounter: Msg()
object GetCounter : Msg()
data class SomeOtherMsg(val something: String) : Msg()
