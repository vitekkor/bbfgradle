// Original bug: KT-31352

@Deprecated("")
@get:JvmName("mapIntIntHex")
val Map<Int, Int>.hex: String get() = TODO()

@Deprecated("")
@get:JvmName("mapIntByteHex")
val Map<Int, Byte>.hex: String get() = TODO()
