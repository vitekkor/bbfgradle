class Recursive<T : Recursive<T>> : Generic<PlaceHolder<T>
>, MainSupertype
class Simple<T> : Generic<T
>, MainSupertype
interface Generic<T>
interface PlaceHolder<T : MainSupertype> : Stub<T
>
interface Stub<T : MainSupertype
>
interface MainSupertype
interface SpecificStub<T : SpecificSimple
> : Stub<T
>
class SpecificSimple : Simple<SpecificStub
>
fun 
(recursive: Recursive<*>
, simpleWithSpecific: Simple<SpecificStub<*>>
) {
when (z) {
 true -> select( recursive,simpleWithSpecific)
 else -> select( recursive,simpleWithSpecific)
}
}
fun <T> select(
x: T,y: T
): T
 = TODO