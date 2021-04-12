// Original bug: KT-12705

open class A(val a: Any)

open class B: A(C.field)

object C: B() {
    @JvmField
    val field = object{}
    
    @JvmField
    val field2 = "String"
}
