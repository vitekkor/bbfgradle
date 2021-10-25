// Original bug: KT-42364

interface A {
  val aaa: Int    
}

class B: A {
    // 'aaa' is configurable
    override val aaa: Int = 13
}

// property 'aaa' is non-configurable
class C(a: A): A by a
