// Original bug: KT-10583

open class LateInitBase {
  open lateinit var value: String
}

object LateInit : LateInitBase() {
  override var value: String
    get() = ""
    set(value) {}
}

fun accessLateInitFromKotlin() {
  LateInit.value = "test"
  check(LateInit.value == "test") // Throws an exception.
}
