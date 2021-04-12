// Original bug: KT-26829

inline class NotField(val x1: UInt)
class WrapNotField(x2: NotField)

inline class NotUnsigned(val x1: Int)
class WrapNotUnsigned(val x2: NotUnsigned)

class NotInline(val x1: UInt)
class WrapNotInline(val x2: NotInline)
