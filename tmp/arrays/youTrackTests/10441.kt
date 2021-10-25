// Original bug: KT-5750

public inline fun <T> having(t : T, builder : T.() -> Unit) : T {
  t.builder()
  return t
}

class A { public var id : String? = null }
class B { public var id : String? = null }
