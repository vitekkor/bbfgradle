// Original bug: KT-34488

class Test(private val capture: Map<String, Any>) {
  fun foo(appendable: Appendable) {
    appendable.apply {
      appendln(outer {
        inner {
          function(1, capture["2"], capture["3"])
        }
        inner {
        }
      }.toString())
      appendln()
    }
  }
}

interface InnerReceiver {
  fun function(vararg stuff: Any?)
}
interface OuterReceiver {
  fun inner(body: InnerReceiver.() -> Unit)
}

class InnerReceiverImpl : InnerReceiver {
  override fun function(vararg stuff: Any?) {}
}
class OuterReceiverImpl : OuterReceiver {
  override fun inner(body: InnerReceiver.() -> Unit) {}
}

fun outer(body: OuterReceiver.() -> Unit): Any {
  return Any()
}
