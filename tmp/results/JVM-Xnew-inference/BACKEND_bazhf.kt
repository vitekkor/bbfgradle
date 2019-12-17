import kotlin.reflect.KProperty
class Delegate<T>(
 inner: T) {
operator fun getValue(
t: Any,p: KProperty<*>): T = TODO()
}
class Foo  {
val A: Foo by Delegate(TODO())
var B: Foo
fun box()   {
B = (
::foo)!!
}
}