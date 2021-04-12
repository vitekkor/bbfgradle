// Original bug: KT-34172


open class BaseClassFromCompanions {
   /// I cannot use @JvmStatic here
   fun thisWillBeStatic() = 42
}

class Example1 { companion object : BaseClassFromCompanions() }
class Example2 { companion object : BaseClassFromCompanions() }

