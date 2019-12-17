import Class.C.genericFromSuper
interface I<G> {
fun genericFromSuper(g: G?) = g
}
open class BaseClass 
class Class {
object C: BaseClass(), I<String>
}
fun box()  {
genericFromSuper(TODO())?.equals(TODO()) ?: (::Nested)
}