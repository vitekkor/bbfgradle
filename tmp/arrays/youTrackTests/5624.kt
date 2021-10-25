// Original bug: KT-20305

sealed class Msg
class IncrementCounter : Msg()
class DecrementCounter: Msg()
class GetCounter : Msg()
data class SomeOtherMsg(val something: String) : Msg()
