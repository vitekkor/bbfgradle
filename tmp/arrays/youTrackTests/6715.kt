// Original bug: KT-18675

interface A {
  val a : String
}

class AImpl(): A {
  override val a = "a"
}

abstract class B: A {
  init { System.out.println(this.a); }
}

class C: A by AImpl(), B() {
}

val c = C()