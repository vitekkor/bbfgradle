// Original bug: KT-36805

interface I {
  val name: String
}

/** Here, the [name] property can be either `open` or `final` -- it doesn't matter. */
abstract class C(@get:JvmName("name") override val name: String) : I
