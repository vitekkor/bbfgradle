// Original bug: KT-27414

inline class InstructionCall(val storage: Long) {
  val opcode: Byte
    get() {
      return (storage shr 56).toByte()
    }

  val argsHolded: Byte
    get() {
      return (storage shr 48).toByte()
    }

  val functionIndex: Short
    get() {
      return (storage shr 32).toShort()
    }

  val firstArg: Short
    get() {
      return (storage shr 16).toShort()
    }

  val secondArg: Short
    get() {
      return (storage shr 0).toShort()
    }

  override fun toString(): String {
    return "call argsHolded=$argsHolded, functionIndex=$functionIndex, firstArg=%$firstArg, secondArg=%$secondArg"
  }

}
