open class C(
 grandParentProp: String
)
fun box()  {
val justForUsageInParentClosure = ""
abstract class B : C {
        val parentProp: String
constructor(arg: Int
): super(justForUsageInParentClosure
) {
            parentProp = 1.toString()
}
}
class A : B {
constructor(x: Int): super(1.plus(
1
)) 
val e =
A()
constructor(): this(
1
)
}
}