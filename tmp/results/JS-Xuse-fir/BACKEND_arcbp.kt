open class C(
 grandParentProp: String
)
fun box()  {
var parentSideEffects: String
val justForUsageInParentClosure = ""
class B : C {
        val parentProp: String
constructor(arg: Int
): super(justForUsageInParentClosure
) {
            parentProp = arg.toString()
}
        init {
parentSideEffects = ""
}
    }
}