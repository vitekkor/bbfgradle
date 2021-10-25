// Original bug: KT-22979

interface I { fun foo(p: String) }
class Impl: I { 
  override fun foo(p2: String) { //if you apply the quickfix here the semantics will be changed
    var p = p2 + p2
    print(p2)
  }
}

