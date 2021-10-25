// Original bug: KT-15807

interface KotlinInterface {
  companion object {
    @JvmField val FOO = Any() // "JvmField cannot be applied to a property defined in companion object of interface"
  }
}
