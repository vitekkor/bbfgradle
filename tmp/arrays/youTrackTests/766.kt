// Original bug: KT-43976

  class MyProto {
    var a = 0
      get() = field
      set(value) {field = value;}
  }

  // DSL builder:
  fun myProto(init: MyProto.() -> Unit) = MyProto().apply(init)

  data class MyBusinessClass (val a: Int) {
    fun asProto() = myProto {
        this.a = a // This is actually a self assignment
        // this.a = this@MyBusinessClass.a // This would have been correct
    }
  }
