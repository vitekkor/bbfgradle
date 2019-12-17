class A {
class Inner(val result: String)
}
fun box()  {
A::Inner.call( (::A)!!.call,TODO()).result
}