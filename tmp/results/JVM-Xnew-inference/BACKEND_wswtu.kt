open class C(
 grandParentProp: String
)
fun box()  {
val justForUsageInClosure = 1
val justForUsageInParentClosure = ""
abstract class B : C {
        val parentProp: String
constructor(arg: Int
): super(justForUsageInParentClosure
) {
            parentProp = arg.toString()
}
}
class A : B {
constructor(x: Int): super(justForUsageInClosure.plus(x
)) 
val e =
A()
constructor(): this(justForUsageInClosure
)
}
}